package workout.workoutapp.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.database.entities.BodyMeasurements;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.BodyMeasurementsRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.service.BodyMeasurementService;
import workout.workoutapp.service.MyProfileService;
import workout.workoutapp.transport.dto.MyProfileDto;
import workout.workoutapp.transport.dto.UserDto;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/myProfile")
public class MyProfileRestController {
    private MyProfileService myProfileService;
    private UserRepository userRepository;
    private BodyMeasurementsRepository bodyMeasurementsRepository;
    private BodyMeasurementService bodyMeasurementService;

    @Autowired
    public MyProfileRestController(MyProfileService myProfileService, UserRepository userRepository, BodyMeasurementsRepository bodyMeasurementsRepository, BodyMeasurementService bodyMeasurementService) {
        this.myProfileService = myProfileService;
        this.bodyMeasurementService = bodyMeasurementService;
        this.userRepository = userRepository;
        this.bodyMeasurementsRepository = bodyMeasurementsRepository;
    }

    @PutMapping("/{email}/{password}")
    public ResponseEntity<?> changePasswordForEmail(@PathVariable String password, @PathVariable String email) {
        return myProfileService.updatePasswordForEmail(email, password) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<MyProfileDto> getBodyMeasurementData(@RequestBody UserDto userDto) {
        Optional<User> userByEmail = userRepository.findByEmail(userDto.getEmail());
        if (userByEmail.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<BodyMeasurements> bodyMeasurements = bodyMeasurementsRepository.findByUser(userByEmail.get());

        return ResponseEntity.ok(new MyProfileDto(bodyMeasurements));
    }

    @PostMapping(value = "/updateProfile")
    public ResponseEntity<?> updateBodyMeasurement(@RequestBody BodyMeasurements bodyMeasurements) {
        boolean toUpdate = bodyMeasurementService.updateBodyMeasurement(bodyMeasurements);

        if (toUpdate) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
