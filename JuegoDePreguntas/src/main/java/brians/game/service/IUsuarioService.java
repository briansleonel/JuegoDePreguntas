/**
 * 
 */
package brians.game.service;

import java.util.List;
import java.util.Optional;

import brians.game.entity.Usuario;

/**
 * @author Gonzalez Brian Leonel
 *
 */
public interface IUsuarioService {
	
	/**
	 * Permite guardar un usuario en la BD
	 * @param unUsuario
	 */
	public void guardarUsuario(Usuario unUsuario);
	
	/**
	 * Permite obtener todos los usuarios almacenados en la BD
	 * @return lista de usuarios
	 */
	public List<Usuario> obtenerTodosUsuarios();
	
	/**
	 * Permite eliminar un usuario de la BD
	 * @param id de usuario
	 */
	public void eliminarUsuario(Long id);
	
	/**
	 * Permite verificar si existe un determinado UserName en la BD
	 * @param username a buscar
	 * @return TRUE si exite, FALSE si no existe
	 */
	public boolean existeUsuario(String username);
	
	/**
	 * Permite obtener un usuario de la BD, de acuerdo a su id
	 * @param id
	 * @return
	 */
	public Optional<Usuario> obtenerUsuario(Long id);
	
	/**
	 * permite extraer un determinado usuario de la BD
	 * @param id
	 * @return
	 */
	public Usuario extraerUsuario(Long id);
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public Usuario buscarUsuario(String username);
	
}
