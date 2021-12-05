package workout.workoutapp.transport.converter;

import workout.workoutapp.database.entities.WorkoutDay;
import workout.workoutapp.transport.dto.WorkoutDaysDto;

public class WorkoutDaysConverter {

    public static WorkoutDay toEntity(WorkoutDaysDto workoutDaysDto){
        return new WorkoutDay(workoutDaysDto.getDateOfTraining(), workoutDaysDto.getNameOfDay(), workoutDaysDto.getTrainingName(), workoutDaysDto.getUser());
    }

    public static WorkoutDaysDto toDto(WorkoutDay workoutDay){
        return new WorkoutDaysDto(workoutDay.getDateOfTraining(), workoutDay.getNameOfDay(), workoutDay.getTrainingName(), workoutDay.getUser());
    }
}
