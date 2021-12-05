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
    private String trainingName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public WorkoutDay(LocalDate dateOfTraining, String nameOfDay, String trainingName, User user) {
        this.dateOfTraining = dateOfTraining;
        this.nameOfDay = nameOfDay;
        this.trainingName = trainingName;
        this.user = user;
    }
}
