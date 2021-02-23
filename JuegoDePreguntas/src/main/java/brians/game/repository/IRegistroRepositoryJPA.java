/**
 * 
 */
package brians.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import brians.game.entity.RegistroJuego;

/**
 * @author Brian's
 *
 */
public interface IRegistroRepositoryJPA extends JpaRepository<RegistroJuego, Long> {

}
