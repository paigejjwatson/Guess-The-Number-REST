package paige.rest.guessthenumber;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoundRepository extends CrudRepository<Round, Integer> {
    List<Round> findAllByGameIdOrderByTime(int gameId);
}
