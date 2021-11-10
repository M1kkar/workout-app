package workout.workoutapp.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BodyMeasurements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurement_id;

    @JoinColumn(name = "user_id")
    @OneToOne(optional = false)
    private User user;

    private Long weight;
    private Long height;
    private Long chest;
    private Long biceps;
    private Long waist;
    private Long hips;

}
