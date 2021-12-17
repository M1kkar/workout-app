package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workout.workoutapp.database.entities.ProductsInDay;

public interface ProductsInDayRepository extends JpaRepository<ProductsInDay, Long> {

}
