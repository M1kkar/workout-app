package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.UserRepository;

import java.util.Optional;


@Service
public class MyProfileService {

    private final UserRepository userRepository;


    @Autowired
    public MyProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    public boolean updatePasswordForEmail(String email, String newPassword){
        Optional<User> byEmail = userRepository.findByEmail(email);

        if(byEmail.isPresent()){
            User user = byEmail.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
