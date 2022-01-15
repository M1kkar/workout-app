package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.workoutapp.database.entities.User;

@Data
@NoArgsConstructor
public class DietDto {
    private float kcal;
    private float protein;
    private float fat;
    private float carbohydrates;
    private User user;

    public DietDto(float kcal, float protein, float fat, float carbohydrates, User user) {
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.user = user;
    }
}
