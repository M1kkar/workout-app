package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workout.workoutapp.database.entities.Products;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    Optional<Products> findByProductName(String name);
}
