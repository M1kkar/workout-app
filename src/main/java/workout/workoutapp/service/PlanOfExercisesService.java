package workout.workoutapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workout.workoutapp.database.entities.Exercises;
import workout.workoutapp.database.entities.PlanOfExercises;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.entities.WorkoutDay;
import workout.workoutapp.database.repository.ExerciseRepository;
import workout.workoutapp.database.repository.PlanOfExercisesRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.transport.moreobjects.DataToAddExercise;
import workout.workoutapp.transport.moreobjects.DataToDeleteExercise;

import java.util.Optional;

@Service
public class PlanOfExercisesService {

    private final WorkoutDayRepository workoutDayRepository;
    private final PlanOfExercisesRepository planOfExercisesRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public PlanOfExercisesService(WorkoutDayRepository workoutDayRepository, PlanOfExercisesRepository planOfExercisesRepository, UserRepository userRepository, ExerciseRepository exerciseRepository) {
        this.workoutDayRepository = workoutDayRepository;
        this.planOfExercisesRepository = planOfExercisesRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public boolean addExercisesToDay(DataToAddExercise addExercise){
        Optional<User> byEmail = userRepository.findByEmail(addExercise.getUser().getEmail());
        Optional<WorkoutDay> byUserAndName = workoutDayRepository.findByTrainingNameAndUser(addExercise.getTrainingName(), byEmail.get());

        Optional<Exercises> byExerciseName = exerciseRepository.findAllByName(addExercise.getExerciseName());

        Optional<PlanOfExercises> byExerciseAndDay = planOfExercisesRepository.findByExercisesAndWorkoutDay(byExerciseName.get(), byUserAndName.get());
        if(byExerciseAndDay.isPresent()){
            return false;
        }

        Long weight = addExercise.getPlanOfExercises().getWeight();
        Long numberOfRepetition = addExercise.getPlanOfExercises().getNumberOfRepetitions();
        Long numberOfSeries = addExercise.getPlanOfExercises().getNumberOfSeries();

        PlanOfExercises planOfExercisesBuilder = PlanOfExercises.builder()
                .exercises(byExerciseName.get())
                .weight(weight)
                .numberOfRepetitions(numberOfRepetition)
                .numberOfSeries(numberOfSeries)
                .workoutDay(byUserAndName.get())
                .build();

        planOfExercisesRepository.save(planOfExercisesBuilder);
        return true;
    }

    public boolean deleteExerciseFromDay(DataToDeleteExercise dataToDeleteExercise){
        Optional<WorkoutDay> workoutDay = workoutDayRepository.findById(dataToDeleteExercise.getWorkoutDay().getWorkout_day_id());
        Optional<Exercises> exercises = exerciseRepository.findById(dataToDeleteExercise.getExercises().getExercise_id());

        Optional<PlanOfExercises> byExercisesAndWorkoutDay = planOfExercisesRepository.findByExercisesAndWorkoutDay(exercises.get(), workoutDay.get());
        planOfExercisesRepository.delete(byExercisesAndWorkoutDay.get());
        return true;
    }
}
