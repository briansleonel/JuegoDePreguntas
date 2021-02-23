package brians.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import brians.game.entity.Categoria;

/**
 * Permite conectarse a la BD y hacer transacciones
 * 
 * @author Gonzalez brian Leonel
 *
 */
public interface ICategoriaRepositoryJPA extends JpaRepository<Categoria, Long> {

	public Categoria findByNombre(String nombre);
}
