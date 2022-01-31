package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.config.error.WorkoutDayException;
import workout.workoutapp.database.entities.PlanOfExercises;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.entities.WorkoutDay;
import workout.workoutapp.database.repository.PlanOfExercisesRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.transport.converter.WorkoutDaysConverter;
import workout.workoutapp.transport.dto.UserDto;
import workout.workoutapp.transport.dto.WorkoutDayDto;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    private final UserRepository userRepository;
    private final WorkoutDayRepository workoutDayRepository;
    private final PlanOfExercisesRepository planOfExercisesRepository;

    @Autowired
    public WorkoutService(UserRepository userRepository, WorkoutDayRepository workoutDayRepository, PlanOfExercisesRepository planOfExercisesRepository) {
        this.userRepository = userRepository;
        this.workoutDayRepository = workoutDayRepository;
        this.planOfExercisesRepository = planOfExercisesRepository;
    }

    public List<WorkoutDayDto> allWorkoutDays(UserDto userDto){
        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());

        return workoutDayRepository.findAllByUser(byEmail.get()).stream().map((WorkoutDaysConverter::toDto)).toList();
    }
    public void addWorkoutDay(WorkoutDayDto workoutDayDto) throws WorkoutDayException {
        Optional<User> byEmail = userRepository.findByEmail(workoutDayDto.getUser().getEmail());
            User user = byEmail.get();
            String name = workoutDayDto.getTrainingName();

            Optional<WorkoutDay> byNameForUser = workoutDayRepository.findByTrainingNameAndUser(name, user);
            if (byNameForUser.isPresent()) {
                throw new WorkoutDayException("Name already exist");

            } else {

                LocalDate date = workoutDayDto.getDateOfTraining();

                workoutDayDto.setTrainingName(name);
                workoutDayDto.setDateOfTraining(date);
                workoutDayDto.setUser(user);

                WorkoutDay workoutDay = WorkoutDaysConverter.toEntity(workoutDayDto);
                workoutDayRepository.save(workoutDay);

            }
        }

    public boolean deleteWorkoutDay(WorkoutDayDto workoutDaysDto) {
        Optional<User> byEmail = userRepository.findByEmail(workoutDaysDto.getUser().getEmail());
        if (byEmail.isPresent()) {
            Optional<WorkoutDay> byName = workoutDayRepository.findByTrainingNameAndUser(workoutDaysDto.getTrainingName(), byEmail.get());
            if (byName.isEmpty()) {
                throw new NoSuchElementException("Empty");
            } else {

                WorkoutDay workoutDay = byName.get();
                List<PlanOfExercises> byWorkoutDay = planOfExercisesRepository.findAllByWorkoutDay(workoutDay);

                planOfExercisesRepository.deleteAll(byWorkoutDay);
                Long id = workoutDay.getWorkout_day_id();
                workoutDayRepository.deleteById(id);

            }

        }
        return true;
    }

}
