package workout.workoutapp.transport.converter;

import workout.workoutapp.database.entities.BodyMeasurements;
import workout.workoutapp.transport.dto.BodyMeasurementsDto;

public class BodyMeasurementsConverter {

    public static BodyMeasurements toEntity(BodyMeasurementsDto bodyMeasurementsDto){
        return new BodyMeasurements(bodyMeasurementsDto.getBiceps(),
                bodyMeasurementsDto.getChest(),
                bodyMeasurementsDto.getHeight(),
                bodyMeasurementsDto.getHips(),
                bodyMeasurementsDto.getWaist(),
                bodyMeasurementsDto.getThigh(),
                bodyMeasurementsDto.getWeight(),
                bodyMeasurementsDto.getUser());
    }

    public static BodyMeasurementsDto toDto(BodyMeasurements bodyMeasurements){
        return new BodyMeasurementsDto(bodyMeasurements.getWeight(),
                bodyMeasurements.getBiceps(),
                bodyMeasurements.getChest(),
                bodyMeasurements.getHeight(),
                bodyMeasurements.getHips(),
                bodyMeasurements.getWaist(),
                bodyMeasurements.getThigh(),
                bodyMeasurements.getUser());
    }
}
