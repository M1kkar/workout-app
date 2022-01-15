package workout.workoutapp.database.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long diet_id;
    private float kcal;
    private float protein;
    private float fat;
    private float carbohydrates;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Diet(float kcal, float protein, float fat, float carbohydrates, User user) {
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.user = user;
    }
}
