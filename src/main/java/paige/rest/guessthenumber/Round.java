package paige.rest.guessthenumber;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roundId;
    private String guess;
    private Time time;
    private String result;
    private int gameId;

}
