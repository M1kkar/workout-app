package workout.workoutapp.transport.moreobjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.workoutapp.database.entities.Exercises;
import workout.workoutapp.database.entities.WorkoutDay;

@Data
@NoArgsConstructor
public class DataToDeleteExercise {
    private Exercises exercises;
    private WorkoutDay workoutDay;

}
