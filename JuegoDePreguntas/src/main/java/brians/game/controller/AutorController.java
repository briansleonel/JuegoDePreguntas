/**
 * 
 */
package brians.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import brians.game.entity.Autor;
import brians.game.service.AutorServiceImp;

/**
 * Controlador de Autores
 * 
 * @author Gonzalez Brian Leonel
 *
 */

@Controller
public class AutorController {
	
	/**
	 * Auto Inyecta un objeto de tipo Autor
	 */
	@Autowired
	Autor unAutor;
	
	/**
	 * Auto Inyecta el servicio para trabajar en la BD con un Autor
	 */
	@Autowired
	AutorServiceImp autorServiceImp;

	@RequestMapping("/autor")
	public String autor(Model model) {
		
		//mandar un objecto de autor
		model.addAttribute("autorForm", unAutor);
		model.addAttribute("autores", autorServiceImp.mostrarAutores());
		
		return "autor";
	}
	
	@PostMapping("/agregarAutor")
	public String agregarAutor(@ModelAttribute("autorForm") Autor autor ,Model model) {
		
		//mandar un objecto de autor
		model.addAttribute("autorForm", unAutor);
		model.addAttribute("autores", autorServiceImp.mostrarAutores());
		
		autorServiceImp.guardarAutor(autor);
		
		return "redirect:/autor";
	}

}
