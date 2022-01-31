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


    private final DietService dietService;


    @Autowired
    public DietRestController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping(value = "/getDiet/{email}")
    public ResponseEntity<DietDto> getDiet(@PathVariable String email) {
        DietDto dietDto = dietService.getDiet(email);
        return ResponseEntity.ok(dietDto);
    }

    @PostMapping(value = "/updateDiet")
    public ResponseEntity<?> updateDiet(@RequestBody DietDto dietDto) throws UserDoesNotExistException {
        dietService.updateDiet(dietDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "getSumOfAll/{date}/{email}")
    public ResponseEntity<SumOfAll> getSumOfAll(@PathVariable String date, @PathVariable String email) {
        SumOfAll sumOfAll = dietService.getSumOfAllProducts(date, email);
        return ResponseEntity.ok(sumOfAll);
    }

    @PostMapping(value = "/generateDiet")
    public ResponseEntity<?> generateDiet(@RequestBody DataToGenerateDiet dataToGenerateDiet) throws UserDoesNotExistException, DietDoesNotExist {

        dietService.createOptimalDiet(dataToGenerateDiet);
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
