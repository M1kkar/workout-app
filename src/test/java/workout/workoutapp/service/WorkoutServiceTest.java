package workout.workoutapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import workout.workoutapp.database.entities.User;
import workout.workoutapp.database.entities.WorkoutDay;
import workout.workoutapp.database.repository.PlanOfExercisesRepository;
import workout.workoutapp.database.repository.UserRepository;
import workout.workoutapp.database.repository.WorkoutDayRepository;
import workout.workoutapp.transport.converter.UserConverter;
import workout.workoutapp.transport.converter.WorkoutDaysConverter;
import workout.workoutapp.transport.dto.UserDto;
import workout.workoutapp.transport.dto.WorkoutDayDto;

import java.time.LocalDate;
import java.util.Optional;

public class WorkoutServiceTest {
    private UserRepository userRepository;
    private WorkoutDayRepository workoutDayRepository;
    private PlanOfExercisesRepository planOfExercisesRepository;
    private WorkoutService workoutService;

    @BeforeEach
    void setUp(){
        this.userRepository = Mockito.mock(UserRepository.class);
        this.workoutDayRepository = Mockito.mock(WorkoutDayRepository.class);
        this.planOfExercisesRepository = Mockito.mock(PlanOfExercisesRepository.class);
        this.workoutService = new WorkoutService(userRepository, workoutDayRepository, planOfExercisesRepository);
    }

    @Test
    void should_addNewWorkoutDay(){
        //given
        User user = new User(null, "mail@mail.pl", "123456", "karol", "mik");
        WorkoutDay workoutDay = new WorkoutDay(LocalDate.now(), "Trening", user);
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Mockito.when(workoutDayRepository.findByTrainingNameAndUser(workoutDay.getTrainingName(), user)).thenReturn(Optional.empty());
        WorkoutDayDto workoutDayDto = WorkoutDaysConverter.toDto(workoutDay);
        //when
        workoutService.addWorkoutDay(workoutDayDto);
        //then
        WorkoutDay expectedWorkoutDay = new WorkoutDay(LocalDate.now(), "Trening", user);
        Mockito.verify(workoutDayRepository, Mockito.times(1)).save(Mockito.eq(expectedWorkoutDay));

    }

    @Test
    void should_deleteWorkoutDay(){
        //given
        User user = new User(null, "mail@mail.pl", "123456", "karol", "mik");
        WorkoutDay workoutDay = new WorkoutDay(1L ,LocalDate.now(), "Trening", user);
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Mockito.when(workoutDayRepository.findByTrainingNameAndUser(workoutDay.getTrainingName(), user)).thenReturn(Optional.of(workoutDay));
        WorkoutDayDto workoutDayDto = WorkoutDaysConverter.toDto(workoutDay);
        //when
        workoutService.deleteWorkoutDay(workoutDayDto);
        //then
        Mockito.when(workoutDayRepository.findById(1L)).thenReturn(Optional.of(workoutDay)).thenReturn(null);
    }
}
