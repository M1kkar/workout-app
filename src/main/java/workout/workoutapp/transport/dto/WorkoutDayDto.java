package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.workoutapp.database.entities.User;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkoutDayDto {
    private LocalDate dateOfTraining;
    private String trainingName;
    private User user;

    public WorkoutDayDto(LocalDate dateOfTraining, String trainingName, User user) {
        this.dateOfTraining = dateOfTraining;
        this.trainingName = trainingName;
        this.user = user;
    }
}
