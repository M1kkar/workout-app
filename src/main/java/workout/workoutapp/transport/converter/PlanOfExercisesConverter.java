package workout.workoutapp.transport.converter;

import workout.workoutapp.database.entities.PlanOfExercises;
import workout.workoutapp.transport.dto.PlanOfExercisesDto;

public class PlanOfExercisesConverter {

    public static PlanOfExercisesDto toDto(PlanOfExercises planOfExercises){
        return new PlanOfExercisesDto(planOfExercises.getNumberOfSeries(), planOfExercises.getNumberOfRepetitions(), planOfExercises.getWeight(), planOfExercises.getWorkoutDay() , planOfExercises.getExercises());
    }
}
