package paige.rest.guessthenumber;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Log
public class GameRestController {
    @Autowired
    GameService service = new GameServiceImpl();

    @PostMapping(path="/begin")
    public String startGame() {
        Game game = service.startGame();
        return String.format("201 CREATED | Game ID: %d", game.getGameId());
    }

    @GetMapping(path="/game")
    public List<Game> listGames() {
        return service.listGames();
    }

    @GetMapping(path="/game/{id}")
    public Optional<Game> getGame(@PathVariable Integer id) {
        return service.getGame(id); }

}
