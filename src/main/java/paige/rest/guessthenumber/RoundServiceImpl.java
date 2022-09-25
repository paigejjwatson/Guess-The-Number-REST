package paige.rest.guessthenumber;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log
public class RoundServiceImpl implements RoundService {

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private GameService gameService = new GameServiceImpl();

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Round makeGuess(Round guess) {
        //Optional<Game> game = gameService.getGame(guess.getGameId());
        Optional<Game> game = gameRepository.findById(guess.getGameId());
        if (game.isPresent()) {
            System.out.println(game);
            String answer = game.get().getAnswer();
            //String test = gameRepository.findById(game.get().getGameId()).get().;
            String guessString = guess.getGuess();
            String result = this.getResult(guessString, answer);
            guess.setResult(result);

            Time time = Time.valueOf(LocalTime.now());
            guess.setTime(time);

            String win = "e:4:p:0";
            if (result.equals(win)) {
                //gameService.getGame(guess.getGameId()).get().setIn_progress(false);
                game.get().setIn_progress(false);
            }

        }


        return roundRepository.save(guess);
    }

    @Override
    public List<Round> getRounds(int gameId) {
        return roundRepository.findAllByGameIdOrderByTime(gameId);
    }

    public String getResult(String guess, String answer) {
        List<Character> guessList = new ArrayList<>(guess.chars()
                .mapToObj(e -> (char) e).toList());
        List<Character> answerList = new ArrayList<>(answer.chars()
                .mapToObj(e -> (char) e).toList());

        int exact = 0, partial = 0;

        for ( char num : guessList ) {
            for ( char ansNum : answerList ) {
                if ( num == ansNum ) {
                    if ( answerList.indexOf(ansNum) == guessList.indexOf(num) ) {
                        exact += 1;
                    } else {
                        partial += 1;
                    }
                }
            }
        }

        return String.format("e:%d:p:%d", exact, partial);
    }
}
