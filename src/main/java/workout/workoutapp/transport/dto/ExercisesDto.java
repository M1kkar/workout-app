package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExercisesDto {
    private String name;


    public ExercisesDto(String name) {
        this.name = name;

    }
}
