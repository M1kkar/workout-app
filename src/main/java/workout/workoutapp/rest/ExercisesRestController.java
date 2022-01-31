package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.database.repository.CategoryRepository;
import workout.workoutapp.database.repository.ExerciseRepository;
import workout.workoutapp.service.ExercisesService;

import java.util.List;

@RestController
@RequestMapping(value = "/exercises")
public class ExercisesRestController {


    private final ExercisesService exercisesService;

    @Autowired
    public ExercisesRestController(ExercisesService exercisesService) {

        this.exercisesService = exercisesService;

    }

    @PostMapping(value = "/allByCategory")
    public ResponseEntity<List<String>> getAllByCategory(@RequestParam String name) {
        List<String> allExercises = exercisesService.getAllExerciseByCategory(name);
        return ResponseEntity.ok(allExercises);
    }

    @PostMapping(value = "/all")
    public ResponseEntity<List<String>> getAll() {
        List<String> allNames = exercisesService.getAll();
        return ResponseEntity.ok(allNames);
    }

    @GetMapping(value = "/getLink")
    public ResponseEntity<List<String>> getLink(@RequestParam String name) {
        List<String> link = exercisesService.getLink(name);
        return ResponseEntity.ok(link);
    }

}
