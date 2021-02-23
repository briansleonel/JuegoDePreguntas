/**
 * 
 */
package brians.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import brians.game.entity.Categoria;
import brians.game.entity.Pregunta;
import brians.game.entity.Usuario;

/**
 * @author Gonzalez Brian Leonel
 *
 */
public interface IPreguntaRepositoryJPA extends JpaRepository<Pregunta, Long> {
	
	public List<Pregunta> findByUsuario(Usuario usuario);
	
	public List<Pregunta> findByCategoria(Categoria categoria);
	
}
