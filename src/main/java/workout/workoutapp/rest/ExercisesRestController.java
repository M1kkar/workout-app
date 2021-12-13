package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.database.entities.Category;
import workout.workoutapp.database.entities.Exercises;
import workout.workoutapp.database.repository.CategoryRepository;
import workout.workoutapp.database.repository.ExerciseRepository;
import workout.workoutapp.service.PlanOfExercisesService;
import workout.workoutapp.transport.moreobjects.DataToAddExercise;

import java.util.*;

@RestController
@RequestMapping(value="/exercises")
public class ExercisesRestController {

    private final ExerciseRepository exerciseRepository;
    private final CategoryRepository categoryRepository;
    private final PlanOfExercisesService planOfExercisesService;

    @Autowired
    public ExercisesRestController(ExerciseRepository exerciseRepository, CategoryRepository categoryRepository, PlanOfExercisesService planOfExercisesService) {
        this.exerciseRepository = exerciseRepository;
        this.categoryRepository = categoryRepository;
        this.planOfExercisesService = planOfExercisesService;
    }

    @PostMapping(value="/allByCategory")
    public ResponseEntity<List<String>> getAllByCategory(@RequestParam String name){
        Category category = categoryRepository.findCategoryIdByName(name);
        List<Exercises> allExercisesInCategory = exerciseRepository.findAllByCategory(category);
        List<String> allNames = allExercisesInCategory.stream().map(Exercises::getName).toList();

        return ResponseEntity.ok(allNames);
    }

    @PostMapping(value = "/all")
    public ResponseEntity<List<String>> getAll(){
        List<Exercises> allExercises = exerciseRepository.findAll();
        List<String> allNames = allExercises.stream().map(Exercises::getName).toList();

        return ResponseEntity.ok(allNames);
    }

}
