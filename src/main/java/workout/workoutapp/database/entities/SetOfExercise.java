package workout.workoutapp.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class SetOfExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="workout_day_id")
    private WorkoutDay workoutDay;

    @ManyToOne
    @JoinColumn(name="exercise_id")
    private Exercises exercises;
}
