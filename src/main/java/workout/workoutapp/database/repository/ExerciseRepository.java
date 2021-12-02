package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workout.workoutapp.database.entities.Category;
import workout.workoutapp.database.entities.Exercises;

import java.util.*;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercises, Long> {
    List<Exercises> findAllByCategory(Category category);
}
