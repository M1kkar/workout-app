package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workout.workoutapp.database.entities.BodyMeasurements;

@Repository
public interface BodyMeasurementsRepository extends JpaRepository<BodyMeasurements, Long> {

}
