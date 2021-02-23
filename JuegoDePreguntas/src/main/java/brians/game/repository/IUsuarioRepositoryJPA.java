package brians.game.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import brians.game.entity.Usuario;

public interface IUsuarioRepositoryJPA extends JpaRepository<Usuario, Long> {
	
	public Usuario findAllByUsername(String username);
	
	/**
	 * Permite buscar un usuario en la BD, de acuerdo a un determinado nombre de usuario
	 * @param nombre nombreUsuariop
	 * @return el usuario encontrado
	 */
	public Optional<Usuario> findByUsername(String nombreUsuario);

}
