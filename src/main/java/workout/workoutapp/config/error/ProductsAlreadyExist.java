package workout.workoutapp.config.error;

public class ProductsAlreadyExist extends RuntimeException{
    public ProductsAlreadyExist(String msg){
        super(msg);
    }
}
