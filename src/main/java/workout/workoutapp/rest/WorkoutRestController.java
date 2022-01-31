package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workout.workoutapp.config.error.WorkoutDayException;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.service.WorkoutService;
import workout.workoutapp.transport.converter.WorkoutDaysConverter;
import workout.workoutapp.transport.dto.UserDto;
import workout.workoutapp.transport.dto.WorkoutDayDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/myTraining")

public class WorkoutRestController {


    private final WorkoutService workoutService;

    @Autowired
    public WorkoutRestController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping(value = "/workoutDays")
    public ResponseEntity<List<WorkoutDayDto>> allWorkoutDays(@RequestBody UserDto userDto) {
        List<WorkoutDayDto> workoutDayDto = workoutService.allWorkoutDays(userDto);
        return ResponseEntity.ok(workoutDayDto);
    }

    @PostMapping(value = "/addWorkoutDay")
    public ResponseEntity<?> addWorkoutDay(@RequestBody WorkoutDayDto workoutDayDto) throws WorkoutDayException {
        workoutService.addWorkoutDay(workoutDayDto);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping(value = "/deleteDay")
    public ResponseEntity<?> deleteWorkoutDay(@RequestBody WorkoutDayDto workoutDaysDto) {
        boolean trainingToDelete = workoutService.deleteWorkoutDay(workoutDaysDto);

        if (trainingToDelete) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
