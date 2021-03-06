package workout.workoutapp.database.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String surname;

    public User(String email, String password, String name, String surname) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
