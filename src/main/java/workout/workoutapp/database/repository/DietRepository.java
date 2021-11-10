package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workout.workoutapp.database.entities.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {
}
