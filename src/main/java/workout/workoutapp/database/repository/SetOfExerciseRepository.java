package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workout.workoutapp.database.entities.SetOfExercise;

@Repository
public interface SetOfExerciseRepository extends JpaRepository<SetOfExercise,Long> {

}
