/**
 * 
 */
package brians.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import brians.game.entity.Autor;

/**
 * Permite realizar consultas a la BD usando JPA repository
 * @author Brian's
 *
 */
public interface IAutorJPA extends JpaRepository<Autor, Long> {

}
