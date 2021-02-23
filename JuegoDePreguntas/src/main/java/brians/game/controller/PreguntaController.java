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

import brians.game.entity.Pregunta;
import brians.game.service.CategoriaServiceImp;
import brians.game.service.PreguntaServiceImp;
import brians.game.service.UsuarioServiceImp;
import brians.game.utils.ConsultaUsuario;
import brians.game.utils.UserName;

/**
 * Controlador para el mapeo de consultas sobre Preguntas
 * 
 * @author Gonzalez Brian Leonel
 *
 */

@Controller
public class PreguntaController {
	
	@Autowired
	CategoriaServiceImp categoriaService;
	
	@Autowired
	PreguntaServiceImp preguntaService;
	
	@Autowired
	UsuarioServiceImp usuarioService;
	
	@Autowired
	Pregunta unaPregunta;
	
	@Autowired
	ConsultaUsuario consultaUsuario;
	
	@Autowired
	UserName userName;
	
	@GetMapping("/preguntaForm")
	public String mainPregunta(Model model) {
		
		model.addAttribute("preguntaForm", unaPregunta);
		model.addAttribute("categorias", categoriaService.mostraCategorias());
		//model.addAttribute("preguntasList", preguntaService.mostrarPreguntas());
		
		if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		return "preguntasForm";
	}
	
	@PostMapping("/preguntas")
	public String guardarPregunta(@Valid @ModelAttribute("preguntaform") Pregunta pregunta, Model model){
		
		if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		if (pregunta.getPregunta().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe introducir una pregunta");
		} else if (pregunta.getCorrecta().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe introducir la respuesta CORRECTA");
		} else if (pregunta.getOpcion1().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe introducir la OPCIÓN 1");
		} else if (pregunta.getOpcion2().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe introducir la OPCIÓN 2");
		} else if (pregunta.getOpcion3().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe introducir la OPCIÓN 3");
		} else if (pregunta.getCategoria().getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe seleccionar una categoría");
		} else {
			
			pregunta.setEstado(true);
			pregunta.setUsuario(usuarioService.buscarUsuario(userName.extraerNombreUsuario()));
			pregunta.setCategoria(categoriaService.obtenerCategoriaNombre(pregunta.getCategoria().getNombre()));
			
			if(pregunta.getUsuario() == null) {
				model.addAttribute("mensajeError", "¡ATENCIÓN! El usuario no se encontro");
			} else {
				
				preguntaService.guardarPregunta(pregunta);
				
				//extraer el nombre de usuario
				model.addAttribute("preguntasList", preguntaService.buscarPreguntasPorUsuario(userName.extraerNombreUsuario()));
				model.addAttribute("agregarPreg", "¡Se guardó la pregunta con Éxito!");
				
				return "misPreguntas";
			}
			
		}
		
		return mainPregunta(model);
	}
	
	@GetMapping("/misPreguntas")
	public String verMisPreguntas(Model model) {
		model.addAttribute("preguntasList", preguntaService.buscarPreguntasPorUsuario(userName.extraerNombreUsuario()));
		
		if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		return "misPreguntas";
	}
	
	@GetMapping("/editarPregunta/{id}")
	public String editarpregunta(@PathVariable Long id ,Model model) {
		
		this.unaPregunta = preguntaService.extraerPregunta(id);
		
		model.addAttribute("preguntaForm", unaPregunta);
		model.addAttribute("categorias", categoriaService.mostraCategorias());
		
		if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		return "editarPregunta";
	}
	
	@PostMapping("/guardarPreguntaEditada")
	public String guardarPreguntaEditada(@Valid @ModelAttribute("preguntaform") Pregunta pregunta, Model model){
		
		if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		if (pregunta.getPregunta().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe introducir una pregunta");
		} else if (pregunta.getCorrecta().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe introducir la respuesta CORRECTA");
		} else if (pregunta.getOpcion1().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe introducir la OPCIÓN 1");
		} else if (pregunta.getOpcion2().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe introducir la OPCIÓN 2");
		} else if (pregunta.getOpcion3().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe introducir la OPCIÓN 3");
		} else if (pregunta.getCategoria().getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! Debe seleccionar una categoría");
		} else {
			
			pregunta.setEstado(true);
			//pregunta.setUsuario(usuarioService.buscarUsuario(userName.extraerNombreUsuario()));
			pregunta.setCategoria(categoriaService.obtenerCategoriaNombre(pregunta.getCategoria().getNombre()));
			/*
			if(pregunta.getUsuario() == null) {
				model.addAttribute("mensajeError", "¡ATENCIÓN! El usuario no se encontro");
			} else { */
				
				Pregunta preguntaAux = preguntaService.extraerPregunta(this.unaPregunta.getId());
				
				preguntaAux.setPregunta(pregunta.getPregunta());
				preguntaAux.setOpcion1(pregunta.getOpcion1());
				preguntaAux.setOpcion2(pregunta.getOpcion2());
				preguntaAux.setOpcion3(pregunta.getOpcion3());
				preguntaAux.setCorrecta(pregunta.getCorrecta());
				preguntaAux.setCategoria(pregunta.getCategoria());
				preguntaAux.setEstado(true);
				//preguntaAux.setUsuario(pregunta.getUsuario());
				
				preguntaService.guardarPregunta(preguntaAux);
				
				model.addAttribute("preguntaForm", unaPregunta);
				//model.addAttribute("categorias", categoriaService.mostraCategorias());
				
				//extraer el nombre de usuario
				model.addAttribute("preguntasList", preguntaService.buscarPreguntasPorUsuario(usuarioService.extraerUsuario(Long.parseLong("1")).getUsername()));
				model.addAttribute("agregarPreg", "¡Se guardó los cambios de la pregunta con Éxito!");
				
				this.unaPregunta = new Pregunta();
				
				if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
					return "verPreguntasAdmin";
				} else {
					return "misPreguntas";
				}
			}
		
		return "editarpregunta";
	}

	@GetMapping("/cancelarEditPreg")
	public String cancelarEdicionPregunta() {
		return "redirect:/misPreguntas";
	}
	
	@GetMapping("/preguntasAdmin")
	public String verPreguntasAdmin(Model model) {
		model.addAttribute("preguntasList", preguntaService.mostrarPreguntas());
		
		if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		return "verPreguntasAdmin";
	}
	
	@GetMapping("/bloquearPregunta/{id}")
	public String bloquearPregunta(@PathVariable Long id, Model model) {
		
		Pregunta pregunta = preguntaService.extraerPregunta(id);
		
		if(!pregunta.isEstado()) {
			model.addAttribute("msgActivo", "¡La pregunta ya se encuentra inactiva!");
		} else {
			pregunta.setEstado(false);
			preguntaService.guardarPregunta(pregunta);
			
			model.addAttribute("msgActivo", "¡Se a deshabilitado la pregunta!");
		
		}
		return "redirect:/preguntasAdmin";
		//return verPreguntasAdmin(model);
	}
	
	@GetMapping("/habilitarPregunta/{id}")
	public String habilitarPregunta(@PathVariable Long id, Model model) {
		
		Pregunta pregunta = preguntaService.extraerPregunta(id);
		
		if(pregunta.isEstado()) {
			model.addAttribute("msgActivo", "¡La pregunta ya se encuentra activa!");
		} else {
			pregunta.setEstado(true);
			preguntaService.guardarPregunta(pregunta);
			
			model.addAttribute("msgActivo", "¡Se a habilitado la pregunta!");
		
		}
		return "redirect:/preguntasAdmin";
	}
	
	@GetMapping("/cancelarEditPregAdmin")
	public String cancelarEdicionPreguntaAdmin() {
		return "redirect:/preguntasAdmin";
	}

	@GetMapping("/buscarPreguntas")
	public String buscarPreguntaUsuario(Model model) {
		
		if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		model.addAttribute("usuario", consultaUsuario);
		
		return "preguntasPorUserAdmin";
	}
	
	@PostMapping("/buscarPreguntas")
	public String mostrarPreguntasEncontradas(@Valid @ModelAttribute("usuario") ConsultaUsuario consulta, Model model) {
		
		if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioService.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		if (consulta.getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "Debe introducir el Nombre de Usuario a buscar");
		} else if (!usuarioService.existeUsuario(consulta.getNombre())) {
			model.addAttribute("mensajeError", "El Nombre de Usuario Ingresado NO EXISTE");
		} else {
			
			model.addAttribute("preguntasList", preguntaService.buscarPreguntasPorUsuario(consulta.getNombre()));
			model.addAttribute("usuario", consultaUsuario);
		}
		
		return "preguntasPorUserAdmin";
	}
	
}
