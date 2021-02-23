/**
 * 
 */
package brians.game.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import brians.game.entity.Jugador;
import brians.game.entity.Usuario;
import brians.game.service.JugadorServiceImp;
import brians.game.service.UsuarioServiceImp;
import brians.game.utils.UserName;

/**
 * Controlador para mapear a un Usuario
 * 
 * @author Gonzalez Brian Leonel
 *
 */

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioServiceImp usuarioServiceImp;
	
	@Autowired
	JugadorServiceImp jugadorServiceImp;
	
	@Autowired
	Usuario unUsuario;
	
	@Autowired
	UserName userName;
	
	//@Autowired
	//Jugador unJugador;
	
	@RequestMapping("/userForm")
	public String mainAdmin(Model model) {
		
		model.addAttribute("usuarioForm", unUsuario);
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		return "userForm";
	}
	
	@PostMapping("/userForm")
	public String agregarUsuario(@Valid @ModelAttribute("usuarioForm") Usuario usuario, Model model) {
		
		//enviar el navbar
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		//Verificar que no se encuntren valores nulos
		if(usuario.getUsername().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe ingresar un Nombre de Usuario");
		} else if (usuarioServiceImp.existeUsuario(usuario.getUsername())) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! El Nombre de Usuario ingresado no está disponible");
		} else if (usuario.getApellido().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe ingresar su Apellido");
		} else if (usuario.getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe ingresar su Nombre");
		} else if(usuario.getEmail().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe ingresar su Correo Electrónico");
		} else if(usuario.getPassword().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe ingresar su Contraseña");
		} else if(usuario.getPassword().length() < 8) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Su contraseña debe tener más de 8 caracteres");
		} else {
			
			if(usuario.getTipo().equals("admin123")) {
				usuario.setTipo("ADMIN");
			} else {
				usuario.setTipo("AUTOR");
			}
			
			Jugador jugador = new Jugador();
			
			jugador.setNickname(usuario.getUsername());
			jugador.setApellido(usuario.getApellido());
			jugador.setNombre(usuario.getNombre());
			jugador.setActivo(true);
			
			usuario.setJugador(jugador);
			
			usuarioServiceImp.guardarUsuario(usuario);
			
			//jugadorServiceImp.guardarJugador(jugador); 
			if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
				model.addAttribute("usuarioForm", unUsuario);
				model.addAttribute("userGuardado", "¡Se ha registrado el Usuario con éxito!");
				return mainAdmin(model);
			} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
				return "redirect:/allUser";
			} else {
				model.addAttribute("usuarioForm", unUsuario);
				model.addAttribute("userGuardado", "¡Se ha registrado el Usuario con éxito!");
				return mainAdmin(model);
			}
			
		}

		return "userForm";
	}
	
	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(@PathVariable Long id) {
		
		usuarioServiceImp.eliminarUsuario(id);
		
		return "redirect:/allUser";
	}
	
	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable Long id, Model model) {
		
		//enviar el navbar
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		Optional<Usuario> usuario = usuarioServiceImp.obtenerUsuario(id);
		
		this.unUsuario = usuario.get();
		
		model.addAttribute("usuarioForm", this.unUsuario);
		
		return "editarUsuario";
	}
	
	@PostMapping("/guardarUsuarioEditado")
	public String guardarUsuarioEditado(@Valid @ModelAttribute("usuarioForm") Usuario usuario, Model model) {
		
		
		//Verificar que no se encuntren valores nulos
		if(usuario.getUsername().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe ingresar un Nombre de Usuario");
		} else if (usuario.getApellido().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe ingresar su Apellido");
		} else if (usuario.getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe ingresar su Nombre");
		} else if(usuario.getEmail().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe ingresar su Correo Electrónico");
		} else {
			
			if(usuario.getTipo().equals("admin123")) {
				usuario.setTipo("ADMIN");
			} else {
				usuario.setTipo("AUTOR");
			}
			
			//usuarioServiceImp.eliminarUsuario(usuario.getId());
			Usuario usuarioAux = usuarioServiceImp.extraerUsuario(this.unUsuario.getId());
			
			Jugador jugadorAux = jugadorServiceImp.extraerJugador(usuarioAux.getId());
			
			jugadorAux.setNickname(usuarioAux.getUsername());
			jugadorAux.setApellido(usuarioAux.getApellido());
			jugadorAux.setNombre(usuarioAux.getNombre());
			
			//usuarioAux.setId(usuario.getId());
			usuarioAux.setUsername(usuario.getUsername());
			usuarioAux.setApellido(usuario.getApellido());
			usuarioAux.setNombre(usuario.getNombre());
			usuarioAux.setEmail(usuario.getEmail());
			usuarioAux.setTipo(usuario.getTipo());
			usuarioAux.setJugador(jugadorAux);
			
			/*
			if(usuario.getPassword().isEmpty() == false) {
				usuarioAux.setPassword(usuario.getPassword());
			} else {
				
			} */
			
			usuarioServiceImp.guardarUsuario(usuarioAux);
			
			this.unUsuario = new Usuario();
			
			model.addAttribute("usuarioForm", unUsuario);
			model.addAttribute("usuariosList", usuarioServiceImp.obtenerTodosUsuarios());
			
			return "redirect:/allUser";
			
		}
		
		return "editarUsuario";
	}

	@GetMapping("/cancelarEdit")
	public String cancelarEdicion(){
		
		this.unUsuario = new Usuario();
		
		return "redirect:/allUser";
	}
	
	@GetMapping("/allUser")
	public String verUsuariosRegistrados(Model model) {
		
		//enviar el navbar
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		model.addAttribute("usuarioForm", unUsuario);
		model.addAttribute("usuariosList", usuarioServiceImp.obtenerTodosUsuarios());
		
		return "verUsuariosAdmin";
	}
	
}
