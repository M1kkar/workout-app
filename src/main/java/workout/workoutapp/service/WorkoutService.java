package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.entities.WorkoutDay;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.transport.converter.WorkoutDaysConverter;
import workout.workoutapp.transport.dto.WorkoutUserData;

import java.time.LocalDate;
import java.util.*;

@Service
public class WorkoutService {
    private UserRepository userRepository;
    private WorkoutDayRepository workoutDayRepository;

    @Autowired
    public WorkoutService(UserRepository userRepository, WorkoutDayRepository workoutDayRepository) {
        this.userRepository = userRepository;
        this.workoutDayRepository = workoutDayRepository;
    }

    public boolean addWorkoutDay(WorkoutUserData workoutUserData){
        Optional<User> byEmail = userRepository.findByEmail(workoutUserData.getUserData().getEmail());

        if(byEmail.isPresent()) {

            User user = byEmail.get();
            String name = workoutUserData.getWorkoutData().getTrainingName();
            LocalDate date = workoutUserData.getWorkoutData().getDateOfTraining();

            workoutUserData.getWorkoutData().setTrainingName(name);
            workoutUserData.getWorkoutData().setDateOfTraining(date);
            workoutUserData.getWorkoutData().setUser(user);

            WorkoutDay workoutDay = WorkoutDaysConverter.toEntity(workoutUserData.getWorkoutData());
            workoutDayRepository.save(workoutDay);
            return true;
        }

        return false;
    }

}
