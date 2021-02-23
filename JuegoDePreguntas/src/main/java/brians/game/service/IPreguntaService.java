/**
 * 
 */
package brians.game.service;

import java.util.List;

import brians.game.entity.Categoria;
import brians.game.entity.Pregunta;

/**
 * @author Gonzalez Brian Leonel
 *
 */
public interface IPreguntaService {
	
	/**
	 * Permite guardar una pregunta en la BD
	 * @param unaPregunta
	 */
	public void guardarPregunta(Pregunta unaPregunta);
	
	/**
	 * Permite eliminar una pregunta de la BD
	 * @param id de la pregunta
	 */
	public void eliminarPregunta(Long id);
	
	/**
	 * permite listar todas las preguntas de la BD
	 * @return lista de preguntas
	 */
	public List<Pregunta> mostrarPreguntas();
	
	/**
	 * Permite buscar las preguntas, de acuerdo a un determinado usuario
	 * @param username del usuario a buscar
	 * @return preguntas encontradas
	 */
	public List<Pregunta> buscarPreguntasPorUsuario(String username);
	
	/**
	 * Permite buscar en la BD una pregunta, de acuerdo al ID
	 * @param id pregunta
	 * @return pregunta encontrada
	 */
	public Pregunta extraerPregunta(Long id);
	
	/**
	 * Permite alamacenar una categoria de preguntas a buscar
	 * @param categoria
	 */
	public void almacenarCategoriaSeleccionada(Categoria categoria);
	
	/**
	 * Permite vaciar la categoria seleccionada
	 */
	public void vaciarCategoriaSeleccionada();
	
	public List<Pregunta> buscarPreguntasPorCategoria(Categoria categoria);

}
