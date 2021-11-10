package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workout.workoutapp.database.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}