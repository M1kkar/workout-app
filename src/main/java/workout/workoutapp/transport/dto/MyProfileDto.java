package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.workoutapp.database.entities.BodyMeasurements;
import workout.workoutapp.database.entities.User;

import java.util.*;
import java.util.Optional;

@Data
@NoArgsConstructor
public class MyProfileDto {
    private Optional<BodyMeasurements> bodyMeasurements;


    public MyProfileDto(Optional<BodyMeasurements> bodyMeasurements) {
        this.bodyMeasurements = bodyMeasurements;
    }
}
