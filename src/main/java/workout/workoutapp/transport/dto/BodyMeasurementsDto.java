package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.workoutapp.database.entities.User;

@Data
@NoArgsConstructor
public class BodyMeasurementsDto {
    private Long weight;
    private Long height;
    private Long chest;
    private Long biceps;
    private Long waist;
    private Long hips;
    private Long thigh;
    private User user;

    public BodyMeasurementsDto(Long weight, Long height, Long chest, Long biceps, Long waist, Long hips, Long thigh, User user) {
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
