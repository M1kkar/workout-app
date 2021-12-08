package workout.workoutapp.transport.converter;

import workout.workoutapp.transport.dto.WorkoutDayDto;

public class WorkoutDaysConverter {

    public static workout.workoutapp.database.entities.WorkoutDay toEntity(WorkoutDayDto workoutDaysDto){
        return new workout.workoutapp.database.entities.WorkoutDay(workoutDaysDto.getDateOfTraining(), workoutDaysDto.getTrainingName(), workoutDaysDto.getUser());
    }

    public static WorkoutDayDto toDto(workout.workoutapp.database.entities.WorkoutDay workoutDay){
        return new WorkoutDayDto(workoutDay.getDateOfTraining(), workoutDay.getTrainingName(), workoutDay.getUser());
    }
}
