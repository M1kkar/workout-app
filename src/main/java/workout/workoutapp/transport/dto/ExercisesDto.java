package workout.workoutapp.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExercisesDto {
    private String name;
    private String description;

    public ExercisesDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
