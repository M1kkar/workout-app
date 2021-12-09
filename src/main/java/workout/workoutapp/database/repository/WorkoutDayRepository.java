package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.entities.WorkoutDay;



import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutDayRepository extends JpaRepository<WorkoutDay, Long> {
    List<WorkoutDay> findAllByUser(User user);

    Optional<WorkoutDay> findByTrainingNameAndUser(String name, User user);

    Optional<WorkoutDay> findByTrainingName(String name);



}
