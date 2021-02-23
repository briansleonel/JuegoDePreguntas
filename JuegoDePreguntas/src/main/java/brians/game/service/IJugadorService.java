/**
 * 
 */
package brians.game.service;

import java.util.List;

import brians.game.entity.Jugador;

/**
 * @author Gonzalez Brian Leonel
 *
 */

public interface IJugadorService {

	/**
	 * Permite guardar un Jugador en la BD
	 */
	public void guardarJugador(Jugador unJugador);
	
	/**
	 * Permite extraer un determinado jugador de la Bd.
	 * Usarlo cuando estemos seguro de que existe el jugador.
	 * @param id del jugador
	 * @return el jugador encontrado
	 */
	public Jugador extraerJugador(Long id);
	
	/**
	 * Permite mostrar todos los jugadores de la BD
	 * @return jugadores
	 */
	public List<Jugador> mostrarJugadores();
	
	/**
	 * Permite verificar si existe un determinado NickName registrado en la Bd
	 * @param nickname
	 * @return
	 */
	public boolean existeJugador(String nickname);
	
	/**
	 * Permite buscar un jugador en la BD de acuerdo a su nickname
	 * @param nickname
	 * @return
	 */
	public Jugador buscarJugadorNickname(String nickname);
	
	public void guardarJugadorSeleccionado(Jugador jugador);
	
	public Jugador mostrarJugadorSeleccionado();
	
	public void eliminarJugadorSeleccionado();
}
