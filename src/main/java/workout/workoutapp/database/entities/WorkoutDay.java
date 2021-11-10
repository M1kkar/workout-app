package workout.workoutapp.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class WorkoutDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workout_day_id;

    @ManyToOne
    @JoinColumn(name="plan_id")
    private Plan plan;
    private String dayOfWeek;

    @OneToMany(mappedBy="workoutDay")
    Set<SetOfExercise> setOfExercise;

}
