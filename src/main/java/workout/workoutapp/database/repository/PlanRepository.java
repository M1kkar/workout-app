package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workout.workoutapp.database.entities.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
