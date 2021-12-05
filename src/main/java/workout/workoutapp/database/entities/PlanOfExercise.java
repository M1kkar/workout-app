package workout.workoutapp.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class PlanOfExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long numberOfRepetitions;
    private Long numberOfSeries;
    private Long weight;

    @OneToOne
    @JoinColumn(name="workout_day_id")
    private WorkoutDay workoutDay;


    @ManyToOne
    @JoinColumn(name="exercise_id")
    private Exercises exercises;
}
