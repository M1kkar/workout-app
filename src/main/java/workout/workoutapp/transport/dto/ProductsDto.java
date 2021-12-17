package workout.workoutapp.transport.dto;

import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
public class ProductsDto {

    private String productName;
    private float kcal;
    private float protein;
    private float fat;
    private float carbohydrate;

    public ProductsDto(String productName, float kcal, float protein, float fat, float carbohydrate) {
        this.productName = productName;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
    }
}
