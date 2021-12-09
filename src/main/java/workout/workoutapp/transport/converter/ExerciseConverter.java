package workout.workoutapp.transport.converter;


import workout.workoutapp.database.entities.Exercises;
import workout.workoutapp.transport.dto.ExercisesDto;

public class ExerciseConverter {

    public static Exercises toEntity(ExercisesDto exercisesDto){
        return new Exercises(exercisesDto.getName(), exercisesDto.getDescription());
    }

    public static ExercisesDto toDto(Exercises exercises){
        return new ExercisesDto(exercises.getName(), exercises.getDescription());
    }
}
