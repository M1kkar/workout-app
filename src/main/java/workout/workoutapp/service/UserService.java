package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.config.error.UserAlreadyExistException;
import workout.workoutapp.config.error.UserDoesNotExistException;
import workout.workoutapp.config.error.UserPasswordException;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.transport.converter.UserConverter;
import workout.workoutapp.transport.dto.UserDto;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BodyMeasurementService bodyMeasurementService;
    private final DietService dietService;

    @Autowired
    public UserService(UserRepository userRepository, BodyMeasurementService bodyMeasurementService, DietService dietService) {
        this.userRepository = userRepository;
        this.bodyMeasurementService = bodyMeasurementService;
        this.dietService = dietService;
    }

    public boolean registerNewAccount(UserDto userToRegister) throws UserAlreadyExistException {
        Optional<User> byEmail = userRepository.findByEmail(userToRegister.getEmail());

        if (byEmail.isPresent()) {
            throw new UserAlreadyExistException("User exists");
        }

        String password = userToRegister.getPassword();
        userToRegister.setPassword(password);
        User userFromDto = UserConverter.toEntity(userToRegister);
        userRepository.save(userFromDto);
        bodyMeasurementService.addDefaultMeasurements(userFromDto);
        dietService.addDefaultDiet(userFromDto);
        return true;
    }

    public User loginUser(String email, String password) throws UserDoesNotExistException, UserPasswordException {
        return userRepository.findByEmail(email).map(u -> {
            if (!(Objects.equals(password, u.getPassword()))) {
                throw new UserPasswordException("Password incorrect");
            }
            return u;
        }).orElseThrow(() -> new UserDoesNotExistException("User not found"));
    }



}
