/**
 * 
 */
package brians.game;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author Brian's
 *
 */

@Component
public class AutenticacionSuccessHandler implements AuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		boolean tipoAdmin = false;
		boolean tipoAutor = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ADMIN")) {
				tipoAdmin = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("AUTOR")) {
				tipoAutor = true;
				break;
			} 
		}
		
		if (tipoAdmin) {
			redirectStrategy.sendRedirect(request, response, "/bienvenidoAdmin");
		} else if (tipoAutor) {
				redirectStrategy.sendRedirect(request, response, "/bienvenidoAutor");
		} else{
			throw new IllegalStateException();	
		}
								
				

	}

}
