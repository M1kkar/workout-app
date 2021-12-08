package workout.workoutapp.config.error;

public class WorkoutDayNameAlreadyExist extends RuntimeException{
    public WorkoutDayNameAlreadyExist(String msg){
        super(msg);
    }
}
