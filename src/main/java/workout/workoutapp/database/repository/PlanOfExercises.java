package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workout.workoutapp.database.entities.PlanOfExercise;

@Repository
public interface PlanOfExercises extends JpaRepository<PlanOfExercise,Long> {

}
