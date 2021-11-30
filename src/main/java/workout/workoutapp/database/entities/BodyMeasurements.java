package workout.workoutapp.database.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyMeasurements {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Long thigh;

    public BodyMeasurements(Long weight, Long height, Long chest, Long biceps, Long waist, Long hips, Long thigh, User user) {
        this.weight = weight;
        this.height = height;
        this.chest = chest;
        this.biceps = biceps;
        this.waist = waist;
        this.hips = hips;
        this.thigh = thigh;
        this.user = user;
    }
}
