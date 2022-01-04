package workout.workoutapp.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import workout.workoutapp.config.error.UserDoesNotExistException;
import workout.workoutapp.config.error.UserPasswordException;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.service.UserService;
import workout.workoutapp.transport.converter.UserConverter;
import workout.workoutapp.transport.dto.UserDto;

@RestController
@RequiredArgsConstructor
public class AuthRestController {

    private UserService userService;


    @Autowired
    public AuthRestController(UserService userService) {
        this.userService = userService;


    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserDto userDto) {
        boolean user1 = userService.registerNewAccount(userDto);
        if (user1) {
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto userDto) {
        try {
            User user1 = userService.loginUser(userDto.getEmail(), userDto.getPassword());
            return ResponseEntity.ok(UserConverter.toDto(user1));
        } catch (UserDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        } catch (UserPasswordException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
