package paige.rest.guessthenumber;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gameId;
    private String answer;
    private boolean in_progress = true;

}
