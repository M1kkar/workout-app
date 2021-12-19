package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.Products;
import workout.workoutapp.database.entities.ProductsInDay;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.DietRepository;
import workout.workoutapp.database.repository.ProductsInDayRepository;
import workout.workoutapp.database.repository.ProductsRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.service.ProductsService;
import workout.workoutapp.transport.converter.ProductsConverter;
import workout.workoutapp.transport.dto.ProductsDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductsRestController {

    private final ProductsService productsService;
    private final ProductsRepository productsRepository;
    private final ProductsInDayRepository productsInDayRepository;
    private final UserRepository userRepository;
    private final DietRepository dietRepository;

    @Autowired
    public ProductsRestController(ProductsService productsService, ProductsRepository productsRepository, ProductsInDayRepository productsInDayRepository, UserRepository userRepository, DietRepository dietRepository) {
        this.productsService = productsService;
        this.productsRepository = productsRepository;
        this.productsInDayRepository = productsInDayRepository;
        this.userRepository = userRepository;
        this.dietRepository = dietRepository;
    }


    @PostMapping(value = "/add")
    public ResponseEntity<?> addProducts(@RequestBody ProductsDto productsDto) {
        boolean add = productsService.addProduct(productsDto);
        if (add) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/getAll")
    public ResponseEntity<List<ProductsDto>> getAllProducts() {
        List<Products> all = productsRepository.findAll();

        List<ProductsDto> allDto = all.stream().map(ProductsConverter::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(allDto);
    }

    @PostMapping(value = "/addToDay")
    public ResponseEntity<?> addToDay(@RequestBody ProductsInDay productsInDay) {
        boolean add = productsService.addProductToDay(productsInDay);
        if (add) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(value = "/getAllProductsFromDay/{date}/{email}")
    public ResponseEntity<List<ProductsInDay>> getAllFromDay(@PathVariable String date, @PathVariable String email) {
        LocalDate date1 = LocalDate.parse(date);
        Optional<User> byEmail = userRepository.findByEmail(email);
        Optional<Diet> byUser = dietRepository.findByUser(byEmail.get());

        List<ProductsInDay> allProducts = productsInDayRepository.findAllByDateAndDiet(date1, byUser.get());

        return ResponseEntity.ok(allProducts);
    }

    @DeleteMapping(value = "/deleteFromDay/{id}")
    public ResponseEntity<?> deleteFromDay(@PathVariable Long id) {

        productsInDayRepository.deleteById(id);

        return ResponseEntity.ok(HttpStatus.OK);

    }


}
