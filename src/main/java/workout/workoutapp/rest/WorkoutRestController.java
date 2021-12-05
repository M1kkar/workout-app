package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.entities.WorkoutDay;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.transport.converter.UserConverter;
import workout.workoutapp.transport.converter.WorkoutDaysConverter;
import workout.workoutapp.transport.dto.UserDto;
import workout.workoutapp.transport.dto.WorkoutDaysDto;
import workout.workoutapp.transport.dto.WorkoutUserData;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/myTraining")

public class WorkoutRestController {

    private final WorkoutDayRepository workoutDayRepository;
    private final UserRepository userRepository;

    @Autowired
    public WorkoutRestController(WorkoutDayRepository workoutDayRepository, UserRepository userRepository) {
        this.workoutDayRepository = workoutDayRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/workoutDays")
    public ResponseEntity<List<WorkoutDaysDto>> allWorkoutDays(@RequestBody UserDto userDto) {
        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());

        List<WorkoutDaysDto> allDays = workoutDayRepository.findAllByUser(byEmail.get()).stream().map(WorkoutDaysConverter::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(allDays);
    }

    @PostMapping(value = "/addWorkoutDay")
    public ResponseEntity<?> addWorkoutDay(@RequestBody WorkoutUserData workoutUserData) {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
