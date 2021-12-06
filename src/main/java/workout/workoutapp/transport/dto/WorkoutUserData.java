package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class WorkoutUserData {
    private UserDto userData;
    private WorkoutDaysDto workoutData;

    public WorkoutUserData(UserDto userDto, WorkoutDaysDto workoutDaysDto) {
        this.userData = userDto;
        this.workoutData = workoutDaysDto;
    }
}
