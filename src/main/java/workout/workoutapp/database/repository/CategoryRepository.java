package workout.workoutapp.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workout.workoutapp.database.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryIdByName(String name);
}
