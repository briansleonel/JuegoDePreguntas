/**
 * 
 */
package brians.game.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import brians.game.entity.Usuario;
import brians.game.repository.IUsuarioRepositoryJPA;

/**
 * @author Brian's
 *
 */

@Service
public class LoginUsuarioServiceImp implements UserDetailsService {

	@Autowired
	IUsuarioRepositoryJPA iUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuarioEncontrado = iUsuario.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no existe en la BD"));
		
		List<GrantedAuthority> tipos =new ArrayList<>();
		GrantedAuthority grantedAuthority =new SimpleGrantedAuthority(usuarioEncontrado.getTipo());
		tipos.add(grantedAuthority);
		
		UserDetails user = (UserDetails) new User(username,usuarioEncontrado.getPassword(),tipos);
		
		return user;
	}

}
