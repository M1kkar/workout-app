package workout.workoutapp.config.error;

public class UserDoesNotExistException extends Exception{
    public UserDoesNotExistException(String msg){
        super(msg);
    }
}
