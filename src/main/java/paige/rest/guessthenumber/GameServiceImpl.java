package paige.rest.guessthenumber;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game startGame() {
        Game game = new Game();
        String answer = this.createAnswer();
        game.setAnswer(answer);
        return gameRepository.save(game);
    }

    public String createAnswer() {
        List<Character> vals = new ArrayList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
        StringBuilder answer = new StringBuilder();
        Collections.shuffle(vals);
        for (int i=0; i<4; i++) {
            answer.append(vals.get(i));
        }

        return answer.toString();
    }

    @Override
    public List<Game> listGames() {
        List<Game> games = (List<Game>) gameRepository.findAll();
        for ( Game game : games ) {
            System.out.println("LOOK HERE:");
            System.out.println(game);
            if (game.isIn_progress()) {
                System.out.println(game);
                game.setAnswer("HIDDEN, GAME IN PROGRESS");
            } else {
                System.out.println("nahh..it's done");
            }
        }
        return games;
    }

    @Override
    public Optional<Game> getGame(int gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.get().isIn_progress()) {
            System.out.println(game.get().isIn_progress());
            game.get().setAnswer("HIDDEN, GAME IN PROGRESS");
        }
        return game;
    }

}
