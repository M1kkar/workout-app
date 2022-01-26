package workout.workoutapp.rest;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.config.error.DietDoesNotExist;
import workout.workoutapp.config.error.UserDoesNotExistException;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.ProductsInDay;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.DietRepository;
import workout.workoutapp.database.repository.ProductsInDayRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.service.DietService;
import workout.workoutapp.transport.converter.DietConverter;
import workout.workoutapp.transport.dto.DietDto;
import workout.workoutapp.transport.dto.SumOfAll;
import workout.workoutapp.transport.moreobjects.DataToGenerateDiet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/myDiet")
public class DietRestController {

    private final UserRepository userRepository;
    private final DietRepository dietRepository;
    private final DietService dietService;
    private final ProductsInDayRepository productsInDayRepository;

    @Autowired
    public DietRestController(UserRepository userRepository, DietRepository dietRepository, DietService dietService, ProductsInDayRepository productsInDayRepository) {
        this.userRepository = userRepository;
        this.dietRepository = dietRepository;
        this.dietService = dietService;
        this.productsInDayRepository = productsInDayRepository;
    }

    @GetMapping(value = "/getDiet/{email}")
    public ResponseEntity<DietDto> getDiet(@PathVariable String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);

        Optional<Diet> byUser = dietRepository.findByUser(byEmail.get());
        DietDto dietdto = DietConverter.toDto(byUser.get());

        return ResponseEntity.ok(dietdto);
    }

    @PostMapping(value = "/updateDiet")
    public ResponseEntity<?> updateDiet(@RequestBody DietDto dietDto) throws UserDoesNotExistException {
        dietService.updateDiet(dietDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "getSumOfAll/{date}/{email}")
    public ResponseEntity<SumOfAll> getSumOfAll(@PathVariable String date, @PathVariable String email) {
        LocalDate date1 = LocalDate.parse(date);
        Optional<User> byEmail = userRepository.findByEmail(email);
        Optional<Diet> byUser = dietRepository.findByUser(byEmail.get());
        List<ProductsInDay> all = productsInDayRepository.findAllByDateAndDiet(date1, byUser.get());


        double kcal = all.stream().mapToDouble(ProductsInDay::getKcalPortion).sum();
        double protein = all.stream().mapToDouble(ProductsInDay::getProteinPortion).sum();
        double fat = all.stream().mapToDouble(ProductsInDay::getFatPortion).sum();

        double carbohydrate = all.stream().mapToDouble(ProductsInDay::getCarbohydratePortion).sum();


        SumOfAll sumOfAll = new SumOfAll();

        sumOfAll.setKcal(Precision.round(kcal, 2));
        sumOfAll.setProtein(Precision.round(protein, 2));
        sumOfAll.setFat(Precision.round(fat, 2));
        sumOfAll.setCarbohydrate(Precision.round(carbohydrate, 2));

        return ResponseEntity.ok(sumOfAll);
    }

    @PostMapping(value = "/generateDiet")
    public ResponseEntity<?> generateDiet(@RequestBody DataToGenerateDiet dataToGenerateDiet) throws UserDoesNotExistException, DietDoesNotExist {

        dietService.createOptimalDiet(dataToGenerateDiet);
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
