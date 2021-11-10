package workout.workoutapp.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diet_id;
    private Long kcal;
    private Long protein;
    private Long fat;
    private Long carbohydrates;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
