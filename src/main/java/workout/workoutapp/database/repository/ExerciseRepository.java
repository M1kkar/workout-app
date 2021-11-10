package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workout.workoutapp.database.entities.Exercises;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercises, Long> {
}
