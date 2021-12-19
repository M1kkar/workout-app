package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SumOfAll {
    private double kcal;
    private double protein;
    private double fat;
    private double carbohydrate;
}
