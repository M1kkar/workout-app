package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.config.error.ProductsException;
import workout.workoutapp.database.entities.ProductsInDay;
import workout.workoutapp.service.ProductsService;
import workout.workoutapp.transport.dto.ProductsDto;

import java.util.List;


@RestController
@RequestMapping(value = "/products")
public class ProductsRestController {

    private final ProductsService productsService;

    @Autowired
    public ProductsRestController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addProducts(@RequestBody ProductsDto productsDto) throws ProductsException {
        productsService.addProduct(productsDto);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping(value = "/getAll")
    public ResponseEntity<List<ProductsDto>> getAllProducts() {

        List<ProductsDto> allDto = productsService.getAllProducts();
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
        List<ProductsInDay> products = productsService.getAllFromDay(date, email);
        return ResponseEntity.ok(products);

    }

    @DeleteMapping(value = "/deleteFromDay/{id}")
    public ResponseEntity<?> deleteFromDay(@PathVariable Long id) {

        boolean delete = productsService.deleteFromDay(id);

        if (delete) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }


}
