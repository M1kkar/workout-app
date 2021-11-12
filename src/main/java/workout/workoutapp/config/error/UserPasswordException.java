package workout.workoutapp.config.error;

public class UserPasswordException extends RuntimeException{
    public UserPasswordException(String msg){
        super(msg);
    }
}
