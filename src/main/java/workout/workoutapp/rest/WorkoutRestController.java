package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.entities.WorkoutDay;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.service.WorkoutService;
import workout.workoutapp.transport.converter.WorkoutDaysConverter;
import workout.workoutapp.transport.dto.UserDto;
import workout.workoutapp.transport.dto.WorkoutDaysDto;
import workout.workoutapp.transport.dto.WorkoutUserData;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/myTraining")

public class WorkoutRestController {

    private final WorkoutDayRepository workoutDayRepository;
    private final UserRepository userRepository;
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutRestController(WorkoutDayRepository workoutDayRepository, UserRepository userRepository, WorkoutService workoutService) {
        this.workoutDayRepository = workoutDayRepository;
        this.userRepository = userRepository;
        this.workoutService = workoutService;
    }

    @PostMapping(value = "/workoutDays")
    public ResponseEntity<List<WorkoutDaysDto>> allWorkoutDays(@RequestBody UserDto userDto) {
        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());

        List<WorkoutDaysDto> allDays = workoutDayRepository.findAllByUser(byEmail.get()).stream().map(WorkoutDaysConverter::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(allDays);
    }

    @PostMapping(value = "/addWorkoutDay")
    public ResponseEntity<?> addWorkoutDay(@RequestBody WorkoutUserData workoutUserData) {
        boolean workoutToSave = workoutService.addWorkoutDay(workoutUserData);
        if (workoutToSave) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/deleteDay")
    public ResponseEntity<?> deleteExercise(@RequestBody WorkoutDaysDto workoutDaysDto) throws Exception {
     /*   Optional<User> byEmail = userRepository.findByEmail(workoutDaysDto.getUser().getEmail());
        Optional<WorkoutDay> byName = workoutDayRepository.findByTrainingNameAndUser(workoutDaysDto.getTrainingName(), byEmail.get());

        WorkoutDay workoutDay = byName.get();
        Long id = workoutDay.getWorkout_day_id();
        workoutDayRepository.deleteById(id);*/

        boolean trainingToDelete = workoutService.deleteWorkoutDay(workoutDaysDto);

        if(trainingToDelete){
            return ResponseEntity.ok(HttpStatus.OK);
        } else{
            return ResponseEntity.badRequest().build();
        }

}
}
