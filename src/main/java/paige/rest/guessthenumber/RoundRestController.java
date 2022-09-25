package paige.rest.guessthenumber;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log
public class RoundRestController {

    @Autowired
    RoundService service = new RoundServiceImpl();

    @GetMapping(path="/rounds/{id}")
    public List<Round> getRounds(@PathVariable int id) { return service.getRounds(id); }


    @PostMapping(path="/guess")
    public Round makeGuess(@RequestBody Round guess) { return service.makeGuess(guess); }
}
