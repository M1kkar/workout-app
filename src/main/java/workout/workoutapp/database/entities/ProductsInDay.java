package workout.workoutapp.database.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ProductsInDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;
    private Long productSize;

    @OneToOne
    @JoinColumn(name="product_id")
    private Products products;

    @OneToOne
    @JoinColumn(name="diet_id")
    private Diet diet;

}


