package workout.workoutapp.transport.converter;


import workout.workoutapp.database.entities.Exercises;
import workout.workoutapp.transport.dto.ExercisesDto;

public class ExerciseConverter {


    public static ExercisesDto toDto(Exercises exercises){
        return new ExercisesDto(exercises.getName());
    }
}
