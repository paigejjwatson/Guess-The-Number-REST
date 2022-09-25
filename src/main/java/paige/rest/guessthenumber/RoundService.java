package paige.rest.guessthenumber;

import java.util.List;

public interface RoundService {
    Round makeGuess(Round guess);
    List<Round> getRounds(int gameId);


}
