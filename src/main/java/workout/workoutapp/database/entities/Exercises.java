package workout.workoutapp.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Exercises {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exercise_id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Exercises(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
