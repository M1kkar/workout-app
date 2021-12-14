package workout.workoutapp.transport.dto;

import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
public class ProductsDto {

    private String productName;
    private double kcal;
    private double protein;
    private double fat;
    private double carbohydrate;
}
