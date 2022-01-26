package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.ProductsInDay;

import java.time.LocalDate;
import java.util.*;

public interface ProductsInDayRepository extends JpaRepository<ProductsInDay, Long> {


    List<ProductsInDay> findAllByDateAndDiet(LocalDate date, Diet diet);
}
