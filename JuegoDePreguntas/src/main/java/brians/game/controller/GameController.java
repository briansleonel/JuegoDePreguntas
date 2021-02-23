/**
 * 
 */
package brians.game.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import brians.game.entity.Categoria;
import brians.game.entity.Pregunta;
import brians.game.entity.Respuesta;
import brians.game.service.CategoriaServiceImp;
import brians.game.service.JugadorServiceImp;
import brians.game.service.PreguntaServiceImp;
import brians.game.service.UsuarioServiceImp;
import brians.game.utils.ConsultaUsuario;
import brians.game.utils.UserName;

/**
 * @author Gonzalez Brian Leonel
 *
 */

@Controller
public class GameController {
	
	@Autowired
	ConsultaUsuario consultaJug;
	
	@Autowired
	JugadorServiceImp jugadorSeviceImp;
	
	@Autowired
	UsuarioServiceImp usuarioServiceImp;
	
	@Autowired
	UserName userName;
	
	@Autowired
	Categoria unaCategoria;
	
	@Autowired
	CategoriaServiceImp categoriaServiceImp;
	
	@Autowired
	PreguntaServiceImp preguntaServiceImp;
	
	@Autowired
	Respuesta unaRespuesta;
	
	/*
	 * Varibles de juego
	 */
	@Autowired
	List<Pregunta> preguntasSeleccionadas;
	
	//@Autowired
	int preg;
	
	@Autowired
	List<Respuesta> respuestasJugador;
	
	@GetMapping("/seleccionarJugador")
	public String mainGame(Model model) {
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		model.addAttribute("jugador", consultaJug);
		model.addAttribute("table", false);
		
		return"selectPlayer";
	}
	
	@PostMapping("/buscarJugador")
	public String seleccionarJugador(@Valid @ModelAttribute("jugador") ConsultaUsuario jugador ,Model model) {
		
		model.addAttribute("table", false);
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		if (jugador.getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Ingrese el nombre de usuario a buscar");
		} else if (jugadorSeviceImp.buscarJugadorNickname(jugador.getNombre()) == null) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! No se encontró el jugador");
		} else if (!jugadorSeviceImp.buscarJugadorNickname(jugador.getNombre()).isActivo()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! El jugador no se encuentra activo");
		} else {
			
			jugadorSeviceImp.guardarJugadorSeleccionado(jugadorSeviceImp.buscarJugadorNickname(jugador.getNombre()));
			
			model.addAttribute("mensajeEnc", "¡Se ha seleccionado un Jugador!");
			model.addAttribute("jugador", consultaJug);
			model.addAttribute("jugadorSelect", jugadorSeviceImp.mostrarJugadorSeleccionado());
			model.addAttribute("table", true);
		}
		
		return "selectPlayer";
	}
	
	@GetMapping("/seleccionarCategoria")
	public String seleccionarCategoria(Model model) {
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		if(jugadorSeviceImp.mostrarJugadorSeleccionado().getId() == null) {
			
			model.addAttribute("mensajeError", "¡ATENCIÓN! No se seleccionó ningún Jugador");
			model.addAttribute("jugador", consultaJug);
			model.addAttribute("table", false);
			
			return "selectPlayer";
			
		} else {
			
			model.addAttribute("categoriaForm", unaCategoria);
			model.addAttribute("categorias", categoriaServiceImp.mostrarTodasCategorias());
			model.addAttribute("table", true);
			model.addAttribute("jugadorSelect", jugadorSeviceImp.mostrarJugadorSeleccionado());
			
			return "selectCategoria";
		}
	}
	
	@PostMapping("/seleccionarCategoria")
	public String buscarCategoria(@Valid @ModelAttribute("categoriaForm") Categoria categoria, Model model) {
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		if(categoria.getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! No se seleccionó una categoría");
		} else {
			
			//guardar la categoria seleccionada
			categoriaServiceImp.guardarCategoriaSeleccionada(categoriaServiceImp.obtenerCategoriaNombre(categoria.getNombre()));
			
			model.addAttribute("mensajeEnc", "¡Se ha seleccionado una Categoría!");
			model.addAttribute("categoriaForm", unaCategoria);
			model.addAttribute("categorias", categoriaServiceImp.mostrarTodasCategorias());
			model.addAttribute("jugadorSelect", jugadorSeviceImp.mostrarJugadorSeleccionado());
			model.addAttribute("categoriaSelect", categoriaServiceImp.mostrarCategoriaSeleccionada());
			model.addAttribute("table", true);
			
		}
		
		
		return "selectCategoria";
	}
	
	@GetMapping("/buscandoPreguntas")
	public String buscarPreguntasDeAcuerdoCategoria(Model model) {
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		preguntasSeleccionadas = preguntaServiceImp.buscarPreguntasPorCategoria(categoriaServiceImp.mostrarCategoriaSeleccionada());
		this.preg = 0;
		
		return "playGame";
	}
	
	@GetMapping("/jugar")
	public String playGameNow(Model model) {
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		unaRespuesta.setPregunta(this.preguntasSeleccionadas.get(preg));
		
		model.addAttribute("valueBarProgress", preg +1);
		model.addAttribute("preguntaFormRta", unaRespuesta);
		
		return "jugar";
	}

	@PostMapping("/guardarRespuesta")
	public String verificarYGuardarRespuesta(@ModelAttribute("preguntaFormRta") Respuesta respuesta,Model model) {
		
		model.addAttribute("valueBarProgress", preg +1);
		model.addAttribute("preguntaFormRta", unaRespuesta);
		
		respuesta.setPregunta(unaRespuesta.getPregunta());
		
		if(respuesta.getPregunta().getCorrecta().equals(respuesta.getRespuesta())) {
			
			model.addAttribute("correcta", true);
			
		} else {
			model.addAttribute("incorrecta", true);
		}
		
		return "jugar";
	}
	
	@GetMapping("/cancelarGame")
	public String cancelarJuego() {
		
		categoriaServiceImp.eliminarCategoriaSeleccionada();
		jugadorSeviceImp.eliminarJugadorSeleccionado();
		
		return "redirect:/home";
	}

}
