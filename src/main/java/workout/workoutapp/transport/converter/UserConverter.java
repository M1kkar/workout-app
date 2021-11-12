package workout.workoutapp.transport.converter;

import workout.workoutapp.database.entities.User;
import workout.workoutapp.transport.dto.UserDto;

public class UserConverter {


    public static User toEntity(UserDto userDto){
        return new User(userDto.getEmail(), userDto.getPassword(), userDto.getName(), userDto.getSurname());
    }

    public static UserDto toDto(User user){
        return new UserDto(user.getEmail(), user.getPassword(), user.getName(), user.getSurname());
    }
}
