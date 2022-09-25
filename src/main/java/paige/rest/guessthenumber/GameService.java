package paige.rest.guessthenumber;

import java.util.List;
import java.util.Optional;

public interface GameService {

    Game startGame();
    List<Game> listGames();
    Optional<Game> getGame(int gameId);

}
