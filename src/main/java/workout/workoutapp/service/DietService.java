package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.ProductsInDay;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.DietRepository;
import workout.workoutapp.database.repository.ProductsInDayRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.transport.dto.DietDto;
import workout.workoutapp.transport.dto.SumOfAll;
import workout.workoutapp.transport.dto.UserDto;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

        Long kcal = dietDto.getKcal();
        Long protein = dietDto.getProtein();
        Long fat = dietDto.getFat();
        Long carbohydrates = dietDto.getCarbohydrates();

        Long sum = (protein * 4) + (fat * 9) + (carbohydrates * 4);

        if (sum.equals(kcal)) {
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

}
