/**
 * 
 */
package brians.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import brians.game.service.UsuarioServiceImp;
import brians.game.utils.UserName;

/**
 * Controlador principal
 * 
 * @author Gonzalez Brian Leonel
 *
 */

@Controller
public class MainController {
	
	@Autowired
	UserName userName;
	
	@Autowired
	UsuarioServiceImp usuarioServiceImp;

	@RequestMapping("/home")
	public String paginaPrincipal(Model model) {
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		return "index";
	}
	
	@GetMapping("/bienvenidoAutor")
	public String bienvenidoAutor() {
		
		return "bienvenidoAutor";
	}
	
	@GetMapping("/bienvenidoAdmin")
	public String bienvenidoAdmin() {
		
		return "bienvenidoAdmin";
	}
	
	@GetMapping("/login")
	public String iniciarSesion() {
		
		return "login";
	}

}
