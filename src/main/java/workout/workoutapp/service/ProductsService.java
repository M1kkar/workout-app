package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.config.error.ProductsException;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.Products;
import workout.workoutapp.database.entities.ProductsInDay;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.DietRepository;
import workout.workoutapp.database.repository.ProductsInDayRepository;
import workout.workoutapp.database.repository.ProductsRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.transport.converter.ProductsConverter;
import workout.workoutapp.transport.dto.ProductsDto;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.*;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final DietRepository dietRepository;
    private final ProductsInDayRepository productsInDayRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository, DietRepository dietRepository, ProductsInDayRepository productsInDayRepository, UserRepository userRepository) {
        this.productsRepository = productsRepository;
        this.dietRepository = dietRepository;
        this.productsInDayRepository = productsInDayRepository;
        this.userRepository = userRepository;
    }

    public void addProduct(ProductsDto productsDto) throws ProductsException {
        final int proteinKcal = 4;
        final int fatKcal = 9;
        final int carbohydrateKcal = 4;
        Optional<Products> product = productsRepository.findByProductName(productsDto.getProductName());
        if (product.isPresent()) {
            throw new ProductsException("Product already exist");
        } else {
            float protein = productsDto.getProtein();
            float fat = productsDto.getFat();
            float carbohydrate = productsDto.getCarbohydrate();
            productsDto.setProductName(productsDto.getProductName());

            float kcal = (protein * proteinKcal) + (fat * fatKcal) + (carbohydrate * carbohydrateKcal);

            productsDto.setKcal(kcal);
            Products products = ProductsConverter.toEntity(productsDto);
            productsRepository.save(products);

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

    public boolean deleteFromDay(Long id){
        productsInDayRepository.deleteById(id);
        return true;
    }

    public List<ProductsInDay> getAllFromDay(String date, String email){
        LocalDate date1 = LocalDate.parse(date);
        Optional<User> byEmail = userRepository.findByEmail(email);
        Optional<Diet> byUser = dietRepository.findByUser(byEmail.get());
        List<ProductsInDay> allProducts = productsInDayRepository.findAllByDateAndDiet(date1, byUser.get());

        return allProducts;
    }

    public List<ProductsDto> getAllProducts(){
        List<Products> all = productsRepository.findAll();
        List<ProductsDto> allDto = all.stream().map(ProductsConverter::toDto).toList();

        return allDto;
    }

}
