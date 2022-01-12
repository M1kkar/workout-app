package workout.workoutapp.database.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
public class Exercises {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exercise_id;
    private String link;
    private String name;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Exercises(String name) {
        this.name = name;
    }

    public Exercises(Long exercise_id, String link, String name, Category category) {
        this.exercise_id = exercise_id;
        this.link = link;
        this.name = name;
        this.category = category;
    }
}
