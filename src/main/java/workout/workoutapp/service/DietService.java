package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.DietRepository;

@Service
public class DietService {
    private final DietRepository dietRepository;

    @Autowired
    public DietService(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

    public void addDefaultDiet(User user){
        Diet defaultDiet = Diet.builder()
                .kcal(0L)
                .protein(0L)
                .fat(0L)
                .carbohydrates(0L)
                .user(user).build();

        dietRepository.save(defaultDiet);
    }
}
