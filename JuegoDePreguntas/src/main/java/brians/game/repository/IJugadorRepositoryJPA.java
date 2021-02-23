/**
 * 
 */
package brians.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import brians.game.entity.Jugador;

/**
 * @author Gonzalez Brian Leonel
 *
 */
public interface IJugadorRepositoryJPA extends JpaRepository<Jugador, Long> {

	public Jugador findByNickname(String nickname);
}
