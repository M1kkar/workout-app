package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.transport.converter.UserConverter;
import workout.workoutapp.transport.dto.WorkoutDaysDto;
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

    public boolean addWorkoutDay(WorkoutUserData WorkoutUserData){
        Optional<User> byEmail = userRepository.findByEmail(WorkoutUserData.getUserDto().getEmail());


        return true;
    }

}
