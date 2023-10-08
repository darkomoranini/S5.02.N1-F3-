package cat.itacademy.barcelonactiva.morandini.darko.s05.t02.n01.f3.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.morandini.darko.s05.t02.n01.f3.model.domain.game.Game;
import cat.itacademy.barcelonactiva.morandini.darko.s05.t02.n01.f3.model.domain.player.Player;

public interface IgameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByPlayerId(int playerId);
    void deleteByPlayer(Player player);

}
