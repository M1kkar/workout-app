package workout.workoutapp.transport.moreobjects;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import workout.workoutapp.transport.dto.UserDto;
import workout.workoutapp.transport.dto.WorkoutDayDto;

@Data
@NoArgsConstructor
@Getter
@Setter
public class WorkoutUserData {
    private UserDto userData;
    private WorkoutDayDto workoutData;

    public WorkoutUserData(UserDto userDto, WorkoutDayDto workoutDaysDto) {
        this.userData = userDto;
        this.workoutData = workoutDaysDto;
    }
}
