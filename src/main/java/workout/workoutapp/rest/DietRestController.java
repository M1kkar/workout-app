package workout.workoutapp.rest;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.repository.DietRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.service.DietService;
import workout.workoutapp.transport.converter.DietConverter;
import workout.workoutapp.transport.dto.DietDto;
import workout.workoutapp.transport.dto.MyProfileDto;
import workout.workoutapp.transport.dto.UserDto;

import java.util.Optional;

@RestController
@RequestMapping(value="/myDiet")
public class DietRestController {

    private final UserRepository userRepository;
    private final DietRepository dietRepository;
    private final DietService dietService;

    @Autowired
    public DietRestController(UserRepository userRepository, DietRepository dietRepository, DietService dietService) {
        this.userRepository = userRepository;
        this.dietRepository = dietRepository;
        this.dietService = dietService;
    }

    @GetMapping(value="/getDiet/{email}")
    public ResponseEntity<DietDto> getDiet(@PathVariable String email){
        Optional<User> byEmail = userRepository.findByEmail(email);

        Optional<Diet> byUser = dietRepository.findByUser(byEmail.get());
        DietDto dietdto = DietConverter.toDto(byUser.get());

        return ResponseEntity.ok(dietdto);
    }

    @PostMapping(value="/updateDiet")
    public ResponseEntity<?> updateDiet(@RequestBody DietDto dietDto){
        boolean update = dietService.updateDiet(dietDto);

        if(update){
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
