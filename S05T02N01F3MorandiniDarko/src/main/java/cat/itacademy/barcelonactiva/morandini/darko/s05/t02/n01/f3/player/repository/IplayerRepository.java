package cat.itacademy.barcelonactiva.morandini.darko.s05.t02.n01.f3.player.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.morandini.darko.s05.t02.n01.f3.model.domain.player.Player;

public interface IplayerRepository extends JpaRepository<Player, Integer> {
    Player findByName(String name);

}
