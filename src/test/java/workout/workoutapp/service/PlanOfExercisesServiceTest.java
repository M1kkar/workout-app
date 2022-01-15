package workout.workoutapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import workout.workoutapp.database.entities.*;
import workout.workoutapp.database.repository.ExerciseRepository;
import workout.workoutapp.database.repository.PlanOfExercisesRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.transport.converter.UserConverter;
import workout.workoutapp.transport.dto.UserDto;
import workout.workoutapp.transport.moreobjects.DataToAddExercise;
import workout.workoutapp.transport.moreobjects.DataToDeleteExercise;

import java.time.LocalDate;
import java.util.Optional;

public class PlanOfExercisesServiceTest {
    private WorkoutDayRepository workoutDayRepository;
    private PlanOfExercisesRepository planOfExercisesRepository;
    private UserRepository userRepository;
    private ExerciseRepository exerciseRepository;
    private PlanOfExercisesService planOfExercisesService;

    @BeforeEach
    void setUp(){
        this.workoutDayRepository = Mockito.mock(WorkoutDayRepository.class);
        this.planOfExercisesRepository = Mockito.mock(PlanOfExercisesRepository.class);
        this.userRepository = Mockito.mock(UserRepository.class);
        this.exerciseRepository = Mockito.mock(ExerciseRepository.class);
        this.planOfExercisesService = new PlanOfExercisesService(workoutDayRepository, planOfExercisesRepository, userRepository, exerciseRepository);

    }

    @Test
    void should_addNewExerciseToDay(){
        //given
        Category category = new Category(null, "kategoria1");
        Exercises exercises = new Exercises(null, "link", "Cwiczenie1", category );
        User user = new User(null, "mail@mail.com", "123456", "Karol", "Mik");
        WorkoutDay workoutDay = new WorkoutDay(1L , LocalDate.now(), "Trening", user);
        PlanOfExercises planOfExercises = new PlanOfExercises(null,12L,3L,30L,null,null);
        UserDto userDto = UserConverter.toDto(user);
        DataToAddExercise dataToAddExercise = new DataToAddExercise(exercises.getName(), workoutDay.getTrainingName(), planOfExercises, userDto);
        Mockito.when(userRepository.findByEmail(dataToAddExercise.getUser().getEmail())).thenReturn(Optional.of(user));
        Mockito.when(workoutDayRepository.findByTrainingNameAndUser(dataToAddExercise.getTrainingName(), user)).thenReturn(Optional.of(workoutDay));
        Mockito.when(exerciseRepository.findAllByName(dataToAddExercise.getExerciseName())).thenReturn(Optional.of(exercises));
        Mockito.when(planOfExercisesRepository.findByExercisesAndWorkoutDay(exercises, workoutDay)).thenReturn(Optional.empty());
        //when
        planOfExercisesService.addExercisesToDay(dataToAddExercise);
        //when
        PlanOfExercises expectedPlanOfExercise = new PlanOfExercises(null,12L,3L,30L,workoutDay,exercises );
        Mockito.verify(planOfExercisesRepository, Mockito.times(1)).save(Mockito.eq(expectedPlanOfExercise));
    }

    @Test
    void should_deleteExercise(){
        //given
        Category category = new Category(null, "kategoria1");
        Exercises exercises = new Exercises(1L, "link", "Cwiczenie1", category );
        WorkoutDay workoutDay = new WorkoutDay(1L , LocalDate.now(), "Trening", null);
        DataToDeleteExercise dataToDeleteExercise = new DataToDeleteExercise(exercises, workoutDay);
        PlanOfExercises planOfExercises = new PlanOfExercises(1L,null,null,null,workoutDay,exercises);
        Mockito.when(workoutDayRepository.findById(dataToDeleteExercise.getWorkoutDay().getWorkout_day_id())).thenReturn(Optional.of(workoutDay));
        Mockito.when(exerciseRepository.findById(dataToDeleteExercise.getExercises().getExercise_id())).thenReturn(Optional.of(exercises));
        Mockito.when(planOfExercisesRepository.findByExercisesAndWorkoutDay(exercises,workoutDay)).thenReturn(Optional.of(planOfExercises));
        //when
        planOfExercisesService.deleteExerciseFromDay(dataToDeleteExercise);
        //then
        Mockito.when(planOfExercisesRepository.findById(1L)).thenReturn(Optional.of(planOfExercises)).thenReturn(null);
    }
}

