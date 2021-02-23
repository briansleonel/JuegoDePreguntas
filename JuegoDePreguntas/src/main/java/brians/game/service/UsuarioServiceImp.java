package brians.game.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import brians.game.entity.Usuario;
import brians.game.repository.IUsuarioRepositoryJPA;

@Service
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	private IUsuarioRepositoryJPA usuarioRepositoryJPA;
	
	/**
	 * Guardar un usuario en la BD
	 */
	@Override
	public void guardarUsuario(Usuario unUsuario) {
		String pw = unUsuario.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		unUsuario.setPassword(bCryptPasswordEncoder.encode(pw));
		//bCryptPasswordEncoder.upgradeEncoding(pw);
		usuarioRepositoryJPA.save(unUsuario);
	}

	/**
	 * Permite listar todos los usuarios de la BD
	 */
	@Override
	public List<Usuario> obtenerTodosUsuarios() {
		return usuarioRepositoryJPA.findAll();
	}

	/**
	 * Permite verificar si existe un determinado nombre de usuario en la BD
	 */
	@Override
	public boolean existeUsuario(String username) {
		
		Usuario userEnc = new Usuario();
		userEnc = usuarioRepositoryJPA.findAllByUsername(username);
		
		if(userEnc == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Permite eliminar un usuario de la BD
	 */
	@Override
	public void eliminarUsuario(Long id) {
		usuarioRepositoryJPA.deleteById(id);
	}

	@Override
	public Optional<Usuario> obtenerUsuario(Long id) {
		return usuarioRepositoryJPA.findById(id);
	}

	@Override
	public Usuario extraerUsuario(Long id) {
		
		Optional<Usuario> usuario = usuarioRepositoryJPA.findById(id);
		
		return usuario.get();
	}

	@Override
	public Usuario buscarUsuario(String username) {
		
		return usuarioRepositoryJPA.findAllByUsername(username);
	}
	
	

}
