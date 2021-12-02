package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.database.entities.Category;
import workout.workoutapp.database.entities.Exercises;
import workout.workoutapp.database.repository.CategoryRepository;
import workout.workoutapp.database.repository.ExerciseRepository;

import java.util.*;

@RestController
@RequestMapping(value="/exercises")
public class ExercisesRestController {

    private final ExerciseRepository exerciseRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ExercisesRestController(ExerciseRepository exerciseRepository, CategoryRepository categoryRepository) {
        this.exerciseRepository = exerciseRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping(value="/all")
    public ResponseEntity<List<String>> getAllByCategory(@RequestParam String name){
        Category category = categoryRepository.findCategoryIdByName(name);
        List<Exercises> allExercisesInCategory = exerciseRepository.findAllByCategory(category);
        List<String> allNames = allExercisesInCategory.stream().map(Exercises::getName).toList();

        return ResponseEntity.ok(allNames);
    }
}
