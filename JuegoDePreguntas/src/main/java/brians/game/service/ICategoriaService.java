package brians.game.service;

import java.util.List;
import java.util.Optional;

import brians.game.entity.Categoria;

public interface ICategoriaService {
	
	/**
	 * Permite guardar una categoria en la BDs
	 * @param unaCategoria
	 */
	public void guardarCategoria(Categoria unaCategoria);
	
	/**
	 * Permite eliminar una categoria de la BD
	 * @param id
	 */
	public void eliminarCategoria(Long id);
	
	/**
	 * Permite mostrar todas las categorias guardadas en la BD
	 * @return
	 */
	public List<Categoria> mostraCategorias();
	
	/**
	 * Permite obtener una categoria, de acuerdo a si ID
	 * @param id
	 * @return
	 */
	public Optional<Categoria> obtenerCategoria(Long id);
	
	/**
	 * Permite verificar si existe una categoria en la BD
	 * @param nombre de categoria a buscar
	 * @return TRUE si existe, FALSE en caso contrario
	 */
	public boolean existeCategoria(String nombre);
	
	/**
	 * permite extraer una determinada categoria
	 * @param id
	 * @return
	 */
	public Categoria extraerCategoria(Long id);
	
	/**
	 * Permite obtener una categoria de acuerdo a su nombre
	 * @param nombre
	 * @return
	 */
	public Categoria obtenerCategoriaNombre(String nombre);
	
	public List<Categoria> mostrarTodasCategorias();
	
	public void guardarCategoriaSeleccionada(Categoria unaCategoria);
	
	public Categoria mostrarCategoriaSeleccionada();
	
	public void eliminarCategoriaSeleccionada();

}
