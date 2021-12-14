package workout.workoutapp.transport.converter;

import workout.workoutapp.database.entities.Diet;
import workout.workoutapp.transport.dto.DietDto;

public class DietConverter {

    public static DietDto toDto(Diet diet){
        return new DietDto(diet.getKcal(), diet.getProtein(), diet.getFat(), diet.getCarbohydrates(), diet.getUser());
    }

    public static Diet toEntity(DietDto dietDto){
        return new Diet(dietDto.getKcal(), dietDto.getProtein(), dietDto.getFat(), dietDto.getCarbohydrates(), dietDto.getUser());
    }
}
