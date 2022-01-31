package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.Category;
import workout.workoutapp.database.entities.Exercises;
import workout.workoutapp.database.repository.CategoryRepository;
import workout.workoutapp.database.repository.ExerciseRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ExercisesService {
    private final ExerciseRepository exerciseRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ExercisesService(ExerciseRepository exerciseRepository, CategoryRepository categoryRepository) {
        this.exerciseRepository = exerciseRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<String> getAllExerciseByCategory(String name) {
        Category category = categoryRepository.findCategoryIdByName(name);
        List<Exercises> allExercisesInCategory = exerciseRepository.findAllByCategory(category);

        return allExercisesInCategory.stream().map(Exercises::getName).toList();
    }

    public List<String> getAll() {
        List<Exercises> allExercises = exerciseRepository.findAll();
        return allExercises.stream().map(Exercises::getName).toList();
    }

    public List<String> getLink(String name) {
        Optional<Exercises> byName = exerciseRepository.findByName(name);
        return byName.stream().map(Exercises::getLink).toList();
    }
}
