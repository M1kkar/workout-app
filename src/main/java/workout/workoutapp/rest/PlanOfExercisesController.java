package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.database.entities.Exercises;
import workout.workoutapp.database.entities.PlanOfExercises;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.entities.WorkoutDay;
import workout.workoutapp.database.repository.ExerciseRepository;
import workout.workoutapp.database.repository.PlanOfExercisesRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.service.PlanOfExercisesService;
import workout.workoutapp.transport.converter.PlanOfExercisesConverter;
import workout.workoutapp.transport.converter.WorkoutDaysConverter;
import workout.workoutapp.transport.dto.PlanOfExercisesDto;
import workout.workoutapp.transport.dto.WorkoutDayDto;
import workout.workoutapp.transport.moreobjects.DataToAddExercise;
import workout.workoutapp.transport.moreobjects.DataToDeleteExercise;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/planOfExercises")
public class PlanOfExercisesController {

    private final PlanOfExercisesRepository planOfExercisesRepository;
    private final WorkoutDayRepository workoutDayRepository;
    private final UserRepository userRepository;
    private final PlanOfExercisesService planOfExercisesService;

    @Autowired
    public PlanOfExercisesController(PlanOfExercisesRepository planOfExercisesRepository, WorkoutDayRepository workoutDayRepository, UserRepository userRepository, PlanOfExercisesService planOfExercisesService) {
        this.planOfExercisesRepository = planOfExercisesRepository;
        this.workoutDayRepository = workoutDayRepository;
        this.userRepository = userRepository;
        this.planOfExercisesService = planOfExercisesService;

    }

    @PostMapping(value = "/getPlan")
    public ResponseEntity<List<PlanOfExercisesDto>> getPlanForTraining(@RequestParam String trainingName, @RequestParam String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        Optional<WorkoutDay> byNameAndUser = workoutDayRepository.findByTrainingNameAndUser(trainingName, byEmail.get());

        List<PlanOfExercises> getAllForTraining = planOfExercisesRepository.findAllByWorkoutDay(byNameAndUser.get());

        List<PlanOfExercisesDto> toDto = getAllForTraining.stream().map(PlanOfExercisesConverter::toDto).toList();

        return ResponseEntity.ok(toDto);
    }

    @PostMapping(value = "/addExercises")
    public ResponseEntity<?> addExercises(@RequestBody DataToAddExercise addExercise) {
        boolean add = planOfExercisesService.addExercisesToDay(addExercise);
        if (add) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/deleteExercise")
    public ResponseEntity<?> deleteExercise(@RequestBody DataToDeleteExercise dataToDeleteExercise){
        boolean delete = planOfExercisesService.deleteExerciseFromDay(dataToDeleteExercise);
        if(delete){
            return ResponseEntity.ok(HttpStatus.OK);
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

}
