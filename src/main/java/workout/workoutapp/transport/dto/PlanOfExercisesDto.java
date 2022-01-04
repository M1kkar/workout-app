package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.workoutapp.database.entities.Exercises;
import workout.workoutapp.database.entities.WorkoutDay;

@Data
@NoArgsConstructor
public class PlanOfExercisesDto {
    private Long numberOfRepetitions;
    private Long numberOfSeries;
    private Long weight;
    private WorkoutDay workoutDay;
    private Exercises exercises;

    public PlanOfExercisesDto(Long numberOfRepetitions, Long numberOfSeries, Long weight, WorkoutDay workoutDay, Exercises exercises) {
        this.numberOfRepetitions = numberOfRepetitions;
        this.numberOfSeries = numberOfSeries;
        this.weight = weight;
        this.workoutDay = workoutDay;
        this.exercises = exercises;
    }
}
