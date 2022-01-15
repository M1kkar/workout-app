package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.DietRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.transport.converter.UserConverter;
import workout.workoutapp.transport.dto.DietDto;
import workout.workoutapp.transport.moreobjects.DataToGenerateDiet;

import java.util.Optional;

@Service
public class DietService {
    private final DietRepository dietRepository;
    private final UserRepository userRepository;


    @Autowired
    public DietService(DietRepository dietRepository, UserRepository userRepository) {
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;

    }

    public void addDefaultDiet(User user) {
        Diet defaultDiet = Diet.builder()
                .kcal(0L)
                .protein(0L)
                .fat(0L)
                .carbohydrates(0L)
                .user(user).build();

        dietRepository.save(defaultDiet);
    }


    public boolean updateDiet(DietDto dietDto) {
        Optional<Diet> byUser = dietRepository.findByUser(dietDto.getUser());
        if(byUser.isEmpty()){
            return false;
        }

        float kcal = dietDto.getKcal();
        float protein = dietDto.getProtein();
        float fat = dietDto.getFat();
        float carbohydrates = dietDto.getCarbohydrates();

        float sum = (protein * 4) + (fat * 9) + (carbohydrates * 4);

        if (sum == kcal) {
            Diet diet = byUser.get();

            diet.setKcal(kcal);
            diet.setProtein(protein);
            diet.setFat(fat);
            diet.setCarbohydrates(carbohydrates);

            dietRepository.save(diet);
            return true;

        } else {
            return false;
        }
    }

    public boolean createOptimalDiet(DataToGenerateDiet dataToGenerateDiet){
        Optional<User> user = userRepository.findByEmail(dataToGenerateDiet.getUser().getEmail());
        if(user.isEmpty()){
            return false;
        }
        Optional<Diet> byUser = dietRepository.findByUser(user.get());
        if(byUser.isEmpty()){
            return false;
        }

        float bodyWeight = dataToGenerateDiet.getBodyWeight();
        float weekActivity = dataToGenerateDiet.getWeekActivity();
        float dietTarget = dataToGenerateDiet.getDietTarget();
        float kcal;

        if(dietTarget == 500 || dietTarget == 0) {
             kcal = ((bodyWeight * 22) * weekActivity) + dietTarget;
        } else {
             kcal = ((bodyWeight * 22) * weekActivity) - dietTarget;
        }

        float protein = (float) (kcal * 0.3)/4;
        float fat = (float) (kcal * 0.3)/9;
        float carbohydrate = (float) (kcal * 0.4)/4;

        Diet diet = byUser.get();
        diet.setKcal(kcal);
        diet.setProtein(protein);
        diet.setFat(fat);
        diet.setCarbohydrates(carbohydrate);

        dietRepository.save(diet);
        return true;
    }

}
