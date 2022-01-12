package workout.workoutapp.database.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Builder
@Data
public class PlanOfExercises {
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


    public PlanOfExercises(Long id, Long numberOfRepetitions, Long numberOfSeries, Long weight, WorkoutDay workoutDay, Exercises exercises) {
        this.id = id;
        this.numberOfRepetitions = numberOfRepetitions;
        this.numberOfSeries = numberOfSeries;
        this.weight = weight;
        this.workoutDay = workoutDay;
        this.exercises = exercises;
    }
}
