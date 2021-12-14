package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.workoutapp.database.entities.User;

@Data
@NoArgsConstructor
public class DietDto {
    private Long kcal;
    private Long protein;
    private Long fat;
    private Long carbohydrates;
    private User user;

    public DietDto(Long kcal, Long protein, Long fat, Long carbohydrates, User user) {
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.user = user;
    }
}
