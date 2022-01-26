package workout.workoutapp.service;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.config.error.BadDataForDietException;
import workout.workoutapp.config.error.DietDoesNotExist;
import workout.workoutapp.config.error.UserDoesNotExistException;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.DietRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.transport.dto.DietDto;
import workout.workoutapp.transport.moreobjects.DataToGenerateDiet;

import java.util.Optional;

@Service
public class DietService {
    private final DietRepository dietRepository;
    private final UserRepository userRepository;
    private final int proteinKcal = 4;
    private final int fatKcal = 9;
    private final int carbohydrateKcal = 4;
    private final int kcalToIncreaseWeight = 500;
    private final int numberForWeight = 22;
    private final double proteinPercentInc = 0.2;
    private final double fatPercentRed = 0.25;
    private final double proteinPercentRed = 0.3;
    private final double fatPercentInc = 0.3;
    private final double carbohydratePercentInc = 0.5;
    private final double carbohydratePercentRed = 0.55;


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


    public void updateDiet(DietDto dietDto) throws BadDataForDietException, UserDoesNotExistException{
        Optional<Diet> byUser = dietRepository.findByUser(dietDto.getUser());
        if (byUser.isEmpty()) {
            throw new UserDoesNotExistException("User does not exist");
        }

        float kcal = dietDto.getKcal();
        float protein = dietDto.getProtein();
        float fat = dietDto.getFat();
        float carbohydrates = dietDto.getCarbohydrates();

        float sum = (protein * proteinKcal) + (fat * fatKcal) + (carbohydrates * carbohydrateKcal);

        if (sum != kcal) {
            throw new BadDataForDietException("Bad data for diet");
        }

        Diet diet = byUser.get();

        diet.setKcal(kcal);
        diet.setProtein(protein);
        diet.setFat(fat);
        diet.setCarbohydrates(carbohydrates);

        dietRepository.save(diet);
    }

    public void createOptimalDiet(DataToGenerateDiet dataToGenerateDiet) throws UserDoesNotExistException, DietDoesNotExist {
        Optional<User> user = userRepository.findByEmail(dataToGenerateDiet.getUser().getEmail());
        if (user.isEmpty()) {
            throw new UserDoesNotExistException("user does not exist");
        }
        Optional<Diet> byUser = dietRepository.findByUser(user.get());
        if (byUser.isEmpty()) {
            throw new DietDoesNotExist("diet does not exist");
        }

        float bodyWeight = dataToGenerateDiet.getBodyWeight();
        float weekActivity = dataToGenerateDiet.getWeekActivity();
        float dietTarget = dataToGenerateDiet.getDietTarget();
        float kcal, protein, fat, carbohydrate;

        if (dietTarget == kcalToIncreaseWeight || dietTarget == 0) {
            kcal = ((bodyWeight * numberForWeight) * weekActivity) + dietTarget;
            protein = (float) (kcal * proteinPercentInc) / proteinKcal;
            fat = (float) (kcal * fatPercentInc) / fatKcal;
            carbohydrate = (float) (kcal * carbohydratePercentInc) / carbohydrateKcal;

        } else {
            kcal = ((bodyWeight * numberForWeight) * weekActivity) - dietTarget;
            protein = (float) (kcal * proteinPercentRed) / proteinKcal;
            fat = (float) (kcal * fatPercentRed) / fatKcal;
            carbohydrate = (float) (kcal * carbohydratePercentRed) / carbohydrateKcal;
        }

        Diet diet = byUser.get();
        diet.setKcal(Precision.round(kcal, 1));
        diet.setProtein(Precision.round(protein, 1));
        diet.setFat(Precision.round(fat, 1));
        diet.setCarbohydrates(Precision.round(carbohydrate, 1));

        dietRepository.save(diet);
    }

}