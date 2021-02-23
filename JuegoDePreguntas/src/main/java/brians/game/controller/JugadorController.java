/**
 * 
 */
package brians.game.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import brians.game.entity.Jugador;
import brians.game.service.JugadorServiceImp;
import brians.game.service.UsuarioServiceImp;
import brians.game.utils.UserName;

/**
 * Controlador de mapeo de consultas de Jugador
 * 
 * @author Gonzalez Brian Leonel
 *
 */

@Controller
public class JugadorController {
	
	@Autowired
	JugadorServiceImp jugadorServiceImp;
	
	@Autowired
	Jugador unJugador;
	
	@Autowired
	UserName userName;
	
	@Autowired
	UsuarioServiceImp usuarioServiceImp;
	
	@GetMapping("/newJugador")
	public String mainAdmin(Model model) {
		
		model.addAttribute("jugadorForm", unJugador);
		model.addAttribute("jugadoresList", jugadorServiceImp.mostrarJugadores());
		model.addAttribute("formTab", "active");
		model.addAttribute("tableJug", true);
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
			return "jugadores";
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
			return "jugadoresAdmin";
		} else {
			model.addAttribute("navbar", "header-signup");
			return "jugadores";
		}
	}
	
	@PostMapping("/saveJugador")
	public String agregarJugador(@Valid @ModelAttribute("jugadorForm") Jugador jugador, Model model) {
		
		if (jugador.getNickname().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! No ingresó su NickName");
			model.addAttribute("formTab", "active");
		} else if (jugadorServiceImp.existeJugador(jugador.getNickname())) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! El NickName ingresado está ocupado");
			model.addAttribute("formTab", "active");
		} else if (jugador.getApellido().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe ingresar su Apellido");
			model.addAttribute("formTab", "active");
		} else if (jugador.getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! debe ingresar su Nombre");
			model.addAttribute("formTab", "active");
		} else {
			
			jugador.setActivo(true);
			
			jugadorServiceImp.guardarJugador(jugador);

			model.addAttribute("agregarJug", "¡Jugador guardado con éxito!");
			model.addAttribute("jugadorForm", unJugador);
			model.addAttribute("jugadoresList", jugadorServiceImp.mostrarJugadores());
			model.addAttribute("listTab", "active");
			model.addAttribute("tableJug", true);
			
		}
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
			return "jugadores";
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
			return "jugadoresAdmin";
		} else {
			model.addAttribute("navbar", "header-signup");
			return "jugadores";
		}
		
	}
	
	@GetMapping("/bloquearJugador/{id}")
	public String deshabilitarJugador(@PathVariable Long id, Model model) {
		
		Jugador jugador = jugadorServiceImp.extraerJugador(id);
		
		if (!jugador.isActivo()) {
			model.addAttribute("msgActivo", "¡ATENCIÓN! El Jugador ya se encuentra Inactivo");
		} else {
			jugador.setActivo(false);
			
			jugadorServiceImp.guardarJugador(jugador);
			
		}

		return "redirect:/jugadores";
		
	}
	
	@GetMapping("/habilitarJugador/{id}")
	public String habilitarJugador(@PathVariable Long id, Model model) {
		
		Jugador jugador = jugadorServiceImp.extraerJugador(id);
		
		if(jugador.isActivo()) {
			model.addAttribute("msgActivo", "¡ATENCIÓN! El Jugador ya se encuentra Activo");
		} else {
			jugador.setActivo(true);
			
			jugadorServiceImp.guardarJugador(jugador);
			
		}
		
		return "redirect:/jugadores";
		
	}
	
	@GetMapping("/jugadores")
	public String mostraJugadores(Model model) {
		
		model.addAttribute("jugadorForm", unJugador);
		model.addAttribute("jugadoresList", jugadorServiceImp.mostrarJugadores());
		model.addAttribute("tableJug", true);
		model.addAttribute("listTab", "active");
		
		return "jugadoresAdmin";
	}

}
