/**
 * 
 */
package brians.game.service;

import java.util.List;

import brians.game.entity.Autor;

/**
 * Interfaz que permite usar los servicios para un Autor
 * 
 * @author Gonzalez Brian Leonel
 *
 */
public interface IAutorService {
	
	/**
	 * Metodo que permite guardar un Autor
	 */
	public void guardarAutor(Autor unAutor);
	
	/**
	 * Metodo que permite mostrar todos los autores de la BD
	 * @return la lista de autores
	 */
	public List<Autor> mostrarAutores();

}
