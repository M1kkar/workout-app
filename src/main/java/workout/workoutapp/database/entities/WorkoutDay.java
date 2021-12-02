package workout.workoutapp.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class WorkoutDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workout_day_id;
    private LocalDate dateOfTraining;
    private String nameOfDay;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="workoutDay")
    private List<PlanOfExercise> planOfExercises;

}
