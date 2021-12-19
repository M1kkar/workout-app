package workout.workoutapp.rest;

import org.apache.commons.math3.util.Precision;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.ProductsInDay;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.DietRepository;
import workout.workoutapp.database.repository.ProductsInDayRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.service.DietService;
import workout.workoutapp.transport.converter.DietConverter;
import workout.workoutapp.transport.dto.DietDto;
import workout.workoutapp.transport.dto.MyProfileDto;
import workout.workoutapp.transport.dto.SumOfAll;
import workout.workoutapp.transport.dto.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/myDiet")
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

    @GetMapping(value="/getDiet/{email}")
    public ResponseEntity<DietDto> getDiet(@PathVariable String email){
        Optional<User> byEmail = userRepository.findByEmail(email);

        Optional<Diet> byUser = dietRepository.findByUser(byEmail.get());
        DietDto dietdto = DietConverter.toDto(byUser.get());

        return ResponseEntity.ok(dietdto);
    }

    @PostMapping(value="/updateDiet")
    public ResponseEntity<?> updateDiet(@RequestBody DietDto dietDto){
        boolean update = dietService.updateDiet(dietDto);

        if(update){
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value="getSumOfAll/{date}/{email}")
    public ResponseEntity<SumOfAll> getSumOfAll(@PathVariable String date, @PathVariable String email){
        LocalDate date1 = LocalDate.parse(date);
        Optional<User> byEmail = userRepository.findByEmail(email);
        Optional<Diet> byUser = dietRepository.findByUser(byEmail.get());
        List<ProductsInDay> all = productsInDayRepository.findAllByDateAndDiet(date1, byUser.get());


        double kcal = all.stream().collect(Collectors.summingDouble(ProductsInDay::getKcalPortion));
        double protein = all.stream().collect(Collectors.summingDouble(ProductsInDay::getProteinPortion));
        double fat = all.stream().collect(Collectors.summingDouble(ProductsInDay::getFatPortion));;
        double carbohydrate = all.stream().collect(Collectors.summingDouble(ProductsInDay::getCarbohydratePortion));;



        SumOfAll sumOfAll = new SumOfAll();

        sumOfAll.setKcal(Precision.round(kcal, 2));
        sumOfAll.setProtein(Precision.round(protein, 2));
        sumOfAll.setFat(Precision.round(fat, 2));
        sumOfAll.setCarbohydrate(Precision.round(carbohydrate, 2));

        return ResponseEntity.ok(sumOfAll);
    };
}
