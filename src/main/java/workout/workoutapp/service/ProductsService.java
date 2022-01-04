package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.Products;
import workout.workoutapp.database.entities.ProductsInDay;
import workout.workoutapp.database.repository.DietRepository;
import workout.workoutapp.database.repository.ProductsInDayRepository;
import workout.workoutapp.database.repository.ProductsRepository;
import workout.workoutapp.transport.converter.ProductsConverter;
import workout.workoutapp.transport.dto.ProductsDto;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final DietRepository dietRepository;
    private final ProductsInDayRepository productsInDayRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository, DietRepository dietRepository, ProductsInDayRepository productsInDayRepository) {
        this.productsRepository = productsRepository;
        this.dietRepository = dietRepository;
        this.productsInDayRepository = productsInDayRepository;
    }

    public boolean addProduct(ProductsDto productsDto) {
        Optional<Products> product = productsRepository.findByProductName(productsDto.getProductName());
        if (product.isPresent()) {
            return false;
        } else {
            float protein = productsDto.getProtein();
            float fat = productsDto.getFat();
            float carbohydrate = productsDto.getCarbohydrate();
            productsDto.setProductName(productsDto.getProductName());

            float kcal = (protein * 4) + (fat * 9) + (carbohydrate * 4);

            productsDto.setKcal(kcal);
            Products products = ProductsConverter.toEntity(productsDto);
            productsRepository.save(products);
            return true;
        }
    }

    public boolean addProductToDay(ProductsInDay productsInDay) {
        Optional<Diet> diet = dietRepository.findByUser(productsInDay.getDiet().getUser());
        Optional<Products> product = productsRepository.findByProductName(productsInDay.getProducts().getProductName());

        float size = productsInDay.getProductSize();

        float kcal = product.get().getKcal() * (size / 100);
        float protein = product.get().getProtein() * (size / 100);
        float fat = product.get().getFat() * (size / 100);
        float carbohydrate = product.get().getCarbohydrate() * (size / 100);
        LocalDateTime now = LocalDateTime.now();

        ProductsInDay productsInDayBuilder = ProductsInDay.builder()
                .carbohydratePortion(carbohydrate)
                .date(now.toLocalDate())
                .fatPortion(fat)
                .kcalPortion(kcal)
                .productSize(size)
                .proteinPortion(protein)
                .diet(diet.get())
                .products(product.get())
                .build();

        productsInDayRepository.save(productsInDayBuilder);
        return true;
    }


}
