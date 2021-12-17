package workout.workoutapp.database.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class ProductsInDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float productSize;
    private float kcalPortion;
    private float proteinPortion;
    private float fatPortion;
    private float carbohydratePortion;
    private LocalDate date;


    @OneToOne
    @JoinColumn(name="product_id")
    private Products products;

    @OneToOne
    @JoinColumn(name="diet_id")
    private Diet diet;

    public ProductsInDay(float productSize, float kcalPortion, float proteinPortion, float fatPortion, float carbohydratePortion, LocalDate date, Products products, Diet diet) {
        this.productSize = productSize;
        this.kcalPortion = kcalPortion;
        this.proteinPortion = proteinPortion;
        this.fatPortion = fatPortion;
        this.carbohydratePortion = carbohydratePortion;
        this.date = date;
        this.products = products;
        this.diet = diet;
    }
}


