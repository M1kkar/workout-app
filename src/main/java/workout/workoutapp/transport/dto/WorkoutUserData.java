package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkoutUserData {
    private UserDto userDto;
    private WorkoutDaysDto workoutDaysDto;

    public WorkoutUserData(UserDto userDto, WorkoutDaysDto workoutDaysDto) {
        this.userDto = userDto;
        this.workoutDaysDto = workoutDaysDto;
    }
}
