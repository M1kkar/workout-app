package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.config.error.ExercisesException;
import workout.workoutapp.config.error.UserDoesNotExistException;
import workout.workoutapp.config.error.WorkoutDayException;
import workout.workoutapp.service.PlanOfExercisesService;
import workout.workoutapp.transport.dto.PlanOfExercisesDto;
import workout.workoutapp.transport.moreobjects.DataToAddExercise;
import workout.workoutapp.transport.moreobjects.DataToDeleteExercise;

import java.util.List;

@RestController
@RequestMapping(value = "/planOfExercises")
public class PlanOfExercisesController {


    private final PlanOfExercisesService planOfExercisesService;

    @Autowired
    public PlanOfExercisesController(PlanOfExercisesService planOfExercisesService) {

        this.planOfExercisesService = planOfExercisesService;

    }

    @PostMapping(value = "/getPlan")
    public ResponseEntity<List<PlanOfExercisesDto>> getPlanForTraining(@RequestParam String trainingName, @RequestParam String email) {
        List<PlanOfExercisesDto> toDto = planOfExercisesService.getPlanForTraining(trainingName, email);
        return ResponseEntity.ok(toDto);
    }

    @PostMapping(value = "/addExercises")
    public ResponseEntity<?> addExercises(@RequestBody DataToAddExercise addExercise) throws WorkoutDayException, ExercisesException, UserDoesNotExistException {
        planOfExercisesService.addExercisesToDay(addExercise);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping(value = "/deleteExercise")
    public ResponseEntity<?> deleteExercise(@RequestBody DataToDeleteExercise dataToDeleteExercise) {
        boolean delete = planOfExercisesService.deleteExerciseFromDay(dataToDeleteExercise);
        if (delete) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
