package workout.workoutapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import java.util.Optional;

public class ProductsServiceTest {

    private ProductsRepository productsRepository;
    private DietRepository dietRepository;
    private ProductsInDayRepository productsInDayRepository;
    private ProductsService productsService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        this.productsRepository = Mockito.mock(ProductsRepository.class);
        this.dietRepository = Mockito.mock(DietRepository.class);
        this.productsInDayRepository = Mockito.mock(ProductsInDayRepository.class);
        this.userRepository = Mockito.mock(UserRepository.class);
        this.productsService = new ProductsService(productsRepository, dietRepository, productsInDayRepository, userRepository);
    }

    @Test
    void should_addNewProduct(){
        //given
        Products products = new Products(null, "Szynka konserwowa", 0 ,19, 3, 2 );
        Mockito.when(productsRepository.findByProductName(products.getProductName())).thenReturn(Optional.empty());
        ProductsDto productsDto = ProductsConverter.toDto(products);
        //when
        productsService.addProduct(productsDto);
        //then
        Products expectedProduct = new Products(null, "Szynka konserwowa", 111, 19, 3, 2);
        Mockito.verify(productsRepository, Mockito.times(1)).save(Mockito.eq(expectedProduct));

    }
    @Test
    void should_addProductToDay(){
        //given
        User user = new User(1L,"mail@mail.com", "123456", "Karol", "Mik");
        Diet diet = new Diet(1L, 2702L, 142L, 90L, 331L, user);
        Products product = new Products(1L ,"Kajzerka", 297, (float) 9.2, (float) 3.6, (float) 56.7);
        ProductsInDay productsInDay = new ProductsInDay(100, 297, (float)9.2, (float)3.6, (float)56.7, LocalDate.now(), product, diet);
        Mockito.when(dietRepository.findByUser(user)).thenReturn(Optional.of(diet));
        Mockito.when(productsRepository.findByProductName(productsInDay.getProducts().getProductName())).thenReturn(Optional.of(product));
        //when
        productsService.addProductToDay(productsInDay);
        //then
        ProductsInDay expectedProduct =  new ProductsInDay(100, 297, (float)9.2, (float)3.6, (float)56.7, LocalDate.now(), product, diet);
        Mockito.verify(productsInDayRepository, Mockito.times(1)).save(Mockito.eq(expectedProduct));

    }

}
