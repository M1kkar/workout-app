package workout.workoutapp.transport.moreobjects;

import lombok.*;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.transport.dto.UserDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DataToGenerateDiet {
    private float bodyWeight;
    private float weekActivity;
    private float dietTarget;
    private UserDto user;
}
