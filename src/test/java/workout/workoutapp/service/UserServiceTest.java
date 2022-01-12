package workout.workoutapp.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.transport.converter.UserConverter;
import workout.workoutapp.transport.dto.UserDto;

import java.util.Optional;
import java.util.regex.Matcher;


class UserServiceTest {


    private UserService userService;
    private UserRepository userRepository;
    private BodyMeasurementService bodyMeasurementService;
    private DietService dietService;

    @BeforeEach
    void setUp() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.bodyMeasurementService = Mockito.mock(BodyMeasurementService.class);
        this.dietService = Mockito.mock(DietService.class);
        this.userService = new UserService(userRepository, bodyMeasurementService, dietService);
    }

    @Test
    void should_RegisterNewUser() {
        //given
        User user = new User(null, "mail@mail.com", "123456", "Karol", "Mik");
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        UserDto userDto = UserConverter.toDto(user);
        //when
        userService.registerNewAccount(userDto);
        //then
        User expectedUser = new User(null, "mail@mail.com", "123456", "Karol", "Mik");
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.eq(expectedUser));
    }
    @Test
    void should_RegisterExistUser(){
        //given
        User user = new User(null, "mail@mail.com", "123456", "Karol", "Mik");
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        UserDto userDto = UserConverter.toDto(user);
        //then
        userService.registerNewAccount(userDto);
    }
}
