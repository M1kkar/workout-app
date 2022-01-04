package workout.workoutapp.transport.converter;

import workout.workoutapp.database.entities.WorkoutDay;
import workout.workoutapp.transport.dto.WorkoutDayDto;

public class WorkoutDaysConverter {

    public static WorkoutDay toEntity(WorkoutDayDto workoutDaysDto){
        return new workout.workoutapp.database.entities.WorkoutDay(workoutDaysDto.getDateOfTraining(), workoutDaysDto.getTrainingName(), workoutDaysDto.getUser());
    }

    public static WorkoutDayDto toDto(WorkoutDay workoutDay){
        return new WorkoutDayDto(workoutDay.getDateOfTraining(), workoutDay.getTrainingName(), workoutDay.getUser());
    }
}
