package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.config.error.WorkoutDayNameAlreadyExist;
import workout.workoutapp.database.entities.PlanOfExercises;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.PlanOfExercisesRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.transport.converter.WorkoutDaysConverter;
import workout.workoutapp.transport.dto.WorkoutDayDto;
import workout.workoutapp.transport.dto.WorkoutUserData;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WorkoutService {
    private UserRepository userRepository;
    private WorkoutDayRepository workoutDayRepository;
    private PlanOfExercisesRepository planOfExercisesRepository;

    @Autowired
    public WorkoutService(UserRepository userRepository, WorkoutDayRepository workoutDayRepository, PlanOfExercisesRepository planOfExercisesRepository) {
        this.userRepository = userRepository;
        this.workoutDayRepository = workoutDayRepository;
        this.planOfExercisesRepository = planOfExercisesRepository;
    }

    public boolean addWorkoutDay(WorkoutUserData workoutUserData) {
        Optional<User> byEmail = userRepository.findByEmail(workoutUserData.getUserData().getEmail());

        if (byEmail.isPresent()) {

            User user = byEmail.get();
            String name = workoutUserData.getWorkoutData().getTrainingName();

            Optional<workout.workoutapp.database.entities.WorkoutDay> byNameForUser = workoutDayRepository.findByTrainingNameAndUser(name, user);
            if (byNameForUser.isPresent()) {
                throw new WorkoutDayNameAlreadyExist("Name already exist");

            } else {

                LocalDate date = workoutUserData.getWorkoutData().getDateOfTraining();

                workoutUserData.getWorkoutData().setTrainingName(name);
                workoutUserData.getWorkoutData().setDateOfTraining(date);
                workoutUserData.getWorkoutData().setUser(user);

                workout.workoutapp.database.entities.WorkoutDay workoutDay = WorkoutDaysConverter.toEntity(workoutUserData.getWorkoutData());
                workoutDayRepository.save(workoutDay);
                return true;

            }
        }

        return false;

    }

    public boolean deleteWorkoutDay(WorkoutDayDto workoutDaysDto) throws Exception {
        Optional<User> byEmail = userRepository.findByEmail(workoutDaysDto.getUser().getEmail());
        Optional<workout.workoutapp.database.entities.WorkoutDay> byName = workoutDayRepository.findByTrainingNameAndUser(workoutDaysDto.getTrainingName(), byEmail.get());


        if (byName.isEmpty()) {
            throw new NoSuchElementException("Empty");
        } else {

            workout.workoutapp.database.entities.WorkoutDay workoutDay = byName.get();
            List<PlanOfExercises> byWorkoutDay = planOfExercisesRepository.findAllByWorkoutDay(workoutDay);

            planOfExercisesRepository.deleteAll(byWorkoutDay);
            Long id = workoutDay.getWorkout_day_id();
            workoutDayRepository.deleteById(id);
            return true;
        }
    }

}
