package workout.workoutapp.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class SetOfExercise {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name="workout_day_id")
    private WorkoutDay workoutDay;

    @ManyToOne
    @JoinColumn(name="exercise_id")
    private Exercises exercises;
}
