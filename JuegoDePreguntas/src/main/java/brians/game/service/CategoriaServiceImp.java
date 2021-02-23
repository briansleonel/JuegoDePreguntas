/**
 * 
 */
package brians.game.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brians.game.entity.Categoria;
import brians.game.repository.ICategoriaRepositoryJPA;

/**
 * @author Gonzalez Brian Leonel
 *
 */

@Service
public class CategoriaServiceImp implements ICategoriaService {

	@Autowired
	ICategoriaRepositoryJPA categoriaRepositoryJPA;
	
	@Autowired
	Categoria categoriaAux;
	
	@Override
	public void guardarCategoria(Categoria unaCategoria) {
		categoriaRepositoryJPA.save(unaCategoria);
	}

	@Override
	public void eliminarCategoria(Long id) {
		categoriaRepositoryJPA.deleteById(id);
	}

	@Override
	public List<Categoria> mostraCategorias() {
		return categoriaRepositoryJPA.findAll().subList(1, categoriaRepositoryJPA.findAll().size());
	}

	@Override
	public Optional<Categoria> obtenerCategoria(Long id) {
		return categoriaRepositoryJPA.findById(id);
	}

	@Override
	public boolean existeCategoria(String nombre) {
		
		Categoria categoriaEnc = new Categoria();
		
		categoriaEnc = categoriaRepositoryJPA.findByNombre(nombre);
		
		if(categoriaEnc == null) {
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public Categoria extraerCategoria(Long id) {
		
		System.out.println(id);
		Optional<Categoria> categoria = categoriaRepositoryJPA.findById(id);
		Categoria cat = new Categoria();
		cat = categoria.get();
		return cat;
	}

	@Override
	public Categoria obtenerCategoriaNombre(String nombre) {
		return categoriaRepositoryJPA.findByNombre(nombre);
	}

	@Override
	public List<Categoria> mostrarTodasCategorias() {
		return categoriaRepositoryJPA.findAll();
	}

	@Override
	public void guardarCategoriaSeleccionada(Categoria unaCategoria) {
		categoriaAux = unaCategoria;
	}

	@Override
	public Categoria mostrarCategoriaSeleccionada() {
		return categoriaAux;
	}

	@Override
	public void eliminarCategoriaSeleccionada() {
		categoriaAux = new Categoria();
	}

}
