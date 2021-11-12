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

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerNewAccount(UserDto userToRegister) throws UserAlreadyExistException{
        Optional<User> byEmail = findByEmail(userToRegister.getEmail());

        if(byEmail.isPresent()){
            throw new UserAlreadyExistException("User exists");
        }
        String password = userToRegister.getPassword();
        userToRegister.setPassword(password);
        User userFromDto = UserConverter.toEntity(userToRegister);
        User savedUser = userRepository.save(userFromDto);
        return true;
     }

     public User loginUser(String email, String password) throws UserDoesNotExistException, UserPasswordException{
        return findByEmail(email).map(u-> {
            if(!(Objects.equals(password, u.getPassword()))){
                throw new UserPasswordException("Password incorrect");
            }
            return u;
        }).orElseThrow(() -> new UserDoesNotExistException("User not found"));
     }


    private Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
