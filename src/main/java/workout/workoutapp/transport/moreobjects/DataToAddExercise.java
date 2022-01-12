package workout.workoutapp.transport.moreobjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.workoutapp.database.entities.PlanOfExercises;
import workout.workoutapp.transport.dto.UserDto;

@Data
@NoArgsConstructor
public class DataToAddExercise {
    private String exerciseName;
    private String trainingName;
    private PlanOfExercises planOfExercises;
    private UserDto user;

    public DataToAddExercise(String exerciseName, String trainingName, PlanOfExercises planOfExercises, UserDto user) {
        this.exerciseName = exerciseName;
        this.trainingName = trainingName;
        this.planOfExercises = planOfExercises;
        this.user = user;
    }
}
