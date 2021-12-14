package workout.workoutapp.database.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long diet_id;
    private Long kcal;
    private Long protein;
    private Long fat;
    private Long carbohydrates;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Diet(Long kcal, Long protein, Long fat, Long carbohydrates, User user) {
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.user = user;
    }
}
