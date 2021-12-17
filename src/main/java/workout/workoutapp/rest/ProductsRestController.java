package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workout.workoutapp.database.entities.Products;
import workout.workoutapp.database.entities.ProductsInDay;
import workout.workoutapp.database.repository.ProductsRepository;
import workout.workoutapp.service.ProductsService;
import workout.workoutapp.transport.converter.ProductsConverter;
import workout.workoutapp.transport.dto.ProductsDto;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductsRestController {

    private final ProductsService productsService;
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsRestController(ProductsService productsService, ProductsRepository productsRepository) {
        this.productsService = productsService;
        this.productsRepository = productsRepository;
    }


    @PostMapping(value = "/add")
    public ResponseEntity<?> addProducts(@RequestBody ProductsDto productsDto){
        boolean add = productsService.addProduct(productsDto);
        if(add){
            return ResponseEntity.ok(HttpStatus.OK);
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/getAll")
    public ResponseEntity<List<ProductsDto>> getAllProducts(){
        List<Products> all = productsRepository.findAll();

        List<ProductsDto> allDto = all.stream().map(ProductsConverter::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(allDto);
    }

    @PostMapping(value = "/addToDay")
    public ResponseEntity<?> addToDay(@RequestBody ProductsInDay productsInDay){
        boolean add = productsService.addProductToDay(productsInDay);
        if(add){
            return ResponseEntity.ok(HttpStatus.OK);
        } else{
            return ResponseEntity.badRequest().build();
        }

    }



}
