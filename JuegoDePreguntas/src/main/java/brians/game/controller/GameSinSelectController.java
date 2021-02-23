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
import brians.game.service.CategoriaServiceImp;
import brians.game.service.PreguntaServiceImp;
import brians.game.service.UsuarioServiceImp;
import brians.game.utils.ConsultaUsuario;
import brians.game.utils.UserName;

/**
 * @author Gonzalez Brian Leonel
 *
 */

@Controller
public class GameSinSelectController {
	
	@Autowired
	UsuarioServiceImp usuarioServiceImp;
	
	@Autowired
	UserName userName;
	
	@Autowired
	ConsultaUsuario consultaJug;
	
	@Autowired
	Categoria unaCategoria;
	
	@Autowired
	CategoriaServiceImp categoriaServiceImp;
	
	@Autowired
	PreguntaServiceImp preguntaServiceImp;
	
	@Autowired
	List<Pregunta> preguntasSeleccionadas;
	
	int numPreg;
	
	@GetMapping("/selectCategory")
	public String seleccionarCategoria(Model model) {
			
		model.addAttribute("categoriaForm", unaCategoria);
		model.addAttribute("categorias", categoriaServiceImp.mostrarTodasCategorias());
			
		return "selectCategory";
	}
	
	@PostMapping("/selectCategory")
	public String buscarCategoria(@Valid @ModelAttribute("categoriaForm") Categoria categoria, Model model) {
		
		if(categoria.getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "¡ATENCIÓN! No se seleccionó una categoría");
		} else {
			
			//guardar la categoria seleccionada
			categoriaServiceImp.guardarCategoriaSeleccionada(categoriaServiceImp.obtenerCategoriaNombre(categoria.getNombre()));
			
			model.addAttribute("mensajeEnc", "¡Se ha seleccionado una Categoría!");
			model.addAttribute("categoriaForm", unaCategoria);
			model.addAttribute("categorias", categoriaServiceImp.mostrarTodasCategorias());
			model.addAttribute("categoriaSelect", categoriaServiceImp.mostrarCategoriaSeleccionada());
			
		}
		
		
		return "selectCategory";
	}
	
	@GetMapping("/lookingForQuestion")
	public String lookingForQuestion(Model model) {
		
		if(categoriaServiceImp.mostrarCategoriaSeleccionada().getId() == null) {
			model.addAttribute("categoriaForm", unaCategoria);
			model.addAttribute("categorias", categoriaServiceImp.mostrarTodasCategorias());
			model.addAttribute("mensajeError", "¡ATENCIÓN! No se seleccionó una categoría");
			return "redirect:/selectCategory";
			
		} else {
			preguntasSeleccionadas = preguntaServiceImp.buscarPreguntasPorCategoria(categoriaServiceImp.mostrarCategoriaSeleccionada());
			this.numPreg = 0;
			
			return "lookingForQuestion";
		}
		
	}
	
	@GetMapping("/playing")
	public String playingGame(Model model) {
		
		//Random random = new Random();
		
		model.addAttribute("valueBarProgress", numPreg +1);
		model.addAttribute("unaPpregunta", preguntasSeleccionadas.get(numPreg));
		//model.addAttribute("card-active", random.nextInt(4));
		
		return "playing";
	}
	
	@GetMapping("/next")
	public String nextQuestions(Model model) {
		
		if(numPreg < preguntasSeleccionadas.size() - 1) {
			
			//Random random = new Random();
			
			numPreg++;
			
			model.addAttribute("valueBarProgress", numPreg +1);
			model.addAttribute("unaPpregunta", preguntasSeleccionadas.get(numPreg));
			//model.addAttribute("card-active", random.nextInt(4)); 
			
			return "playing";
			
		} else {
			
			return "redirect:/selectCategory";
			
		}
		
		
	}
	
	@GetMapping("/cancelGame")
	public String cancelarJuego() {
		
		categoriaServiceImp.eliminarCategoriaSeleccionada();
		
		return "redirect:/home";
	}

}
