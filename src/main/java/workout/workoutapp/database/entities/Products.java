package workout.workoutapp.database.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_id;
    private String productName;
    private float kcal;
    private float protein;
    private float fat;
    private float carbohydrate;

    public Products(String productName, float kcal, float protein, float fat, float carbohydrate) {
        this.productName = productName;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
    }
}
