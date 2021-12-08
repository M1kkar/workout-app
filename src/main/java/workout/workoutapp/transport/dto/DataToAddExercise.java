package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.workoutapp.database.entities.PlanOfExercises;
import workout.workoutapp.database.entities.User;

@Data
@NoArgsConstructor
public class DataToAddExercise {
    private String exerciseName;
    private String trainingName;
    private PlanOfExercises planOfExercises;
    private UserDto user;

    public DataToAddExercise(String exerciseName, String trainingName, PlanOfExercises planOfExercises) {
        this.exerciseName = exerciseName;
        this.trainingName = trainingName;
        this.planOfExercises = planOfExercises;
    }
}
