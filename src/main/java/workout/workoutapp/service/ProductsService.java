package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.Products;
import workout.workoutapp.database.repository.ProductsRepository;
import workout.workoutapp.transport.converter.ProductsConverter;
import workout.workoutapp.transport.dto.ProductsDto;

import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public boolean addProduct(ProductsDto productsDto) {
        Optional<Products> product = productsRepository.findByProductName(productsDto.getProductName());
        if (product.isPresent()) {
            return false;
        } else {
            double protein = productsDto.getProtein();
            double fat = productsDto.getFat();
            double carbohydrate = productsDto.getCarbohydrate();
            productsDto.setProductName(productsDto.getProductName());

            double kcal = (protein * 4) + (fat * 9) + (carbohydrate * 4);

            productsDto.setKcal(kcal);
            Products products = ProductsConverter.toEntity(productsDto);
            productsRepository.save(products);
            return true;


        }


    }
}
