package workout.workoutapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workout.workoutapp.service.ProductsService;
import workout.workoutapp.transport.dto.ProductsDto;

@RestController
@RequestMapping(value = "/addProducts")
public class ProductsRestController {

    private final ProductsService productsService;

    @Autowired
    public ProductsRestController(ProductsService productsService) {
        this.productsService = productsService;
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

}
