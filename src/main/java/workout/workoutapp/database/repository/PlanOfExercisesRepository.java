package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workout.workoutapp.database.entities.PlanOfExercise;
import workout.workoutapp.database.entities.WorkoutDay;
import java.util.*;

@Repository
public interface PlanOfExercisesRepository extends JpaRepository<PlanOfExercise,Long> {
    List<PlanOfExercise> findAllByWorkoutDay(WorkoutDay workoutDay);
}
