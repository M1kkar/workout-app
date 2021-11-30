package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.BodyMeasurements;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.BodyMeasurementsRepository;
import workout.workoutapp.database.repository.UserRepository;

import java.util.Optional;


@Service
public class MyProfileService {

    private final UserRepository userRepository;
    private final BodyMeasurementsRepository bodyMeasurementsRepository;

    @Autowired
    public MyProfileService(UserRepository userRepository, BodyMeasurementsRepository bodyMeasurementsRepository) {
        this.userRepository = userRepository;
        this.bodyMeasurementsRepository = bodyMeasurementsRepository;
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


    /*public boolean updateBodyMeasurement(BodyMeasurements bodyMeasurements){
        Optional<BodyMeasurements> byUser = bodyMeasurementsRepository.findByUser(bodyMeasurements.getUser());

        if(byUser.isPresent()) {
            Long biceps = bodyMeasurements.getBiceps();
            Long height = bodyMeasurements.getHeight();
            Long chest = bodyMeasurements.getChest();
            Long waist = bodyMeasurements.getWaist();
            Long hips = bodyMeasurements.getHips();
            Long thigh = bodyMeasurements.getThigh();
            Long weight = bodyMeasurements.getWeight();

            bodyMeasurements.setBiceps(biceps);
            bodyMeasurements.setThigh(thigh);
            bodyMeasurements.setHeight(height);
            bodyMeasurements.setChest(chest);
            bodyMeasurements.setWaist(waist);
            bodyMeasurements.setHips(hips);
            bodyMeasurements.setThigh(thigh);
            bodyMeasurements.setWeight(weight);

            bodyMeasurementsRepository.save(bodyMeasurements);
            return true;
        }
        return false;

    }

    public void addDefaultMeasurements(User user){
            BodyMeasurements bodyMeasurementsBuilder = BodyMeasurements.builder()
                    .biceps(0L)
                    .chest(0L)
                    .height(0L)
                    .user(user)
                    .hips(0L)
                    .weight(0L)
                    .thigh(0L)
                    .waist(0L).build();

            bodyMeasurementsRepository.save(bodyMeasurementsBuilder);
    }*/




}
