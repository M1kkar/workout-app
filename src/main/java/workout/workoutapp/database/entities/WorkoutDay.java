package workout.workoutapp.database.entities;

import lombok.Data;
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
@Data
public class WorkoutDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workout_day_id;
    private LocalDate dateOfTraining;
    private String trainingName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public WorkoutDay(LocalDate dateOfTraining, String trainingName, User user) {
        this.dateOfTraining = dateOfTraining;
        this.trainingName = trainingName;
        this.user = user;
    }

    public WorkoutDay(Long workout_day_id, LocalDate dateOfTraining, String trainingName, User user) {
        this.workout_day_id = workout_day_id;
        this.dateOfTraining = dateOfTraining;
        this.trainingName = trainingName;
        this.user = user;
    }
}
