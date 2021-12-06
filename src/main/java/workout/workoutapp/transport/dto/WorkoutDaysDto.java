package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.entities.WorkoutDay;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkoutDaysDto {
    private LocalDate dateOfTraining;
    private String trainingName;
    private User user;

    public WorkoutDaysDto(LocalDate dateOfTraining, String trainingName, User user) {
        this.dateOfTraining = dateOfTraining;
        this.trainingName = trainingName;
        this.user = user;
    }
}
