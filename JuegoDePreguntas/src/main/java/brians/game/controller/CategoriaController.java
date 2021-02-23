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

import brians.game.entity.Categoria;
import brians.game.service.CategoriaServiceImp;
import brians.game.service.UsuarioServiceImp;
import brians.game.utils.UserName;

/**
 * Controlador para realizar mapeo correspondiente a consultas de categoria, eliminación, etc
 * 
 * @author González Brian Leonel
 *
 */

@Controller
public class CategoriaController {
	
	@Autowired
	Categoria unaCategoria;
	
	@Autowired
	UserName userName;
	
	@Autowired
	CategoriaServiceImp categoriaServiceImp;
	
	@Autowired
	UsuarioServiceImp usuarioServiceImp;
	
	@GetMapping("/categorias")
	public String mainCategoria(Model model) {
		
		this.unaCategoria = new Categoria();
		
		model.addAttribute("categoriaForm", unaCategoria);
		model.addAttribute("categoriasList", categoriaServiceImp.mostraCategorias());
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		return "categorias";
	}
	
	@GetMapping("/categoriasAdmin")
	public String mainCategoriaAdmin(Model model) {
		
		this.unaCategoria = new Categoria();
		
		model.addAttribute("categoriaForm", unaCategoria);
		model.addAttribute("categoriasList", categoriaServiceImp.mostraCategorias());
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		return "categoriasAdmin";
	}
	
	@PostMapping("/agregarCategoria")
	public String agregarCategoria(@Valid @ModelAttribute("categoriaForm") Categoria categoria ,Model model) {
		
		model.addAttribute("categoriasList", categoriaServiceImp.mostraCategorias());
		
		if(categoria.getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "No se ingresó el Nombre de la Categoría");
		} else if(categoriaServiceImp.existeCategoria(categoria.getNombre())) {
			model.addAttribute("mensajeError", "La categoría ya EXISTE");
		} else {
			categoria.setNombre(categoria.getNombre().toUpperCase());
			
			categoriaServiceImp.guardarCategoria(categoria);
			
			model.addAttribute("categoriaForm", unaCategoria);
			model.addAttribute("categoriasList", categoriaServiceImp.mostraCategorias());
			model.addAttribute("categoriaSave", "¡Se ha guardado la categoría con éxito!");
			
			if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
				model.addAttribute("navbar", "header-principal");
				return "categorias";
			} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
				model.addAttribute("navbar", "header-admin");
				return "categoriasAdmin";
			} else {
				model.addAttribute("navbar", "header-signup");
				return "categorias";
			}
		}
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
			return "categorias";
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
			return "categoriasAdmin";
		} else {
			model.addAttribute("navbar", "header-signup");
			return "categorias";
		}
		
	}
	
	@GetMapping("/eliminarCategoria/{id}")
	public String eliminarCategoria(@PathVariable Long id) {
		
		categoriaServiceImp.eliminarCategoria(id);
		
		return "redirect:/categoriasAdmin";
	}
	
	@GetMapping("/editarCategoria/{id}")
	public String editarCategoria(@PathVariable Long id ,Model model) {
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
		} else {
			model.addAttribute("navbar", "header-signup");
		}
		
		Optional<Categoria> categoria = categoriaServiceImp.obtenerCategoria(id);
		
		//Categoria categoriaAux = categoria.get();
		this.unaCategoria = categoria.get();
		
		model.addAttribute("categoriaForm", this.unaCategoria);
		
		return "editarCategoria";
	}

	@PostMapping("/guardarCambiosCategoria")
	public String guardarCambiosCategoria(@Valid @ModelAttribute("categoriaForm") Categoria categoria ,Model model) {
		
		model.addAttribute("categoriasList", categoriaServiceImp.mostraCategorias());
		
		if(categoria.getNombre().isEmpty()) {
			model.addAttribute("mensajeError", "No se ingresó el Nombre de la Categoría");
		} else if(categoriaServiceImp.existeCategoria(categoria.getNombre())) {
			model.addAttribute("mensajeError", "La categoría ya EXISTE");
		} else {
			categoria.setNombre(categoria.getNombre().toUpperCase());
			
			Categoria categoriaAux = categoriaServiceImp.extraerCategoria(this.unaCategoria.getId());
			
			categoriaAux.setId(this.unaCategoria.getId());
			categoriaAux.setNombre(categoria.getNombre());
			
			categoriaServiceImp.guardarCategoria(categoriaAux);
			
			model.addAttribute("categoriaForm", unaCategoria);
			model.addAttribute("categoriasList", categoriaServiceImp.mostraCategorias());
			
			this.unaCategoria = new Categoria();
			
		}
		
		if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()) == null) {
			model.addAttribute("navbar", "header-principal");
			return "categorias";
		} else if(usuarioServiceImp.buscarUsuario(userName.extraerNombreUsuario()).getTipo().equals("ADMIN")) {
			model.addAttribute("navbar", "header-admin");
			return "categoriasAdmin";
		} else {
			model.addAttribute("navbar", "header-signup");
			return "categorias";
		}
		
	}
	
}
