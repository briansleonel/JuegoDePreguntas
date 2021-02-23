/**
 * 
 */
package brians.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brians.game.entity.Jugador;
import brians.game.repository.IJugadorRepositoryJPA;

/**
 * Implementación de servicios de la interfaz de IJugadorService
 * 
 * @author Gonzalez Brian Leonel
 *
 */

@Service
public class JugadorServiceImp implements IJugadorService {
	
	@Autowired
	private Jugador jugadorAux;

	@Autowired
	private IJugadorRepositoryJPA jugadorRepositoryJPA;

	@Override
	public void guardarJugador(Jugador unJugador) {
		jugadorRepositoryJPA.save(unJugador);
	}

	@Override
	public Jugador extraerJugador(Long id) {
		return jugadorRepositoryJPA.findById(id).get();
	}

	@Override
	public List<Jugador> mostrarJugadores() {
		return jugadorRepositoryJPA.findAll();
	}

	@Override
	public boolean existeJugador(String nickname) {
		
		Jugador jugador = jugadorRepositoryJPA.findByNickname(nickname);
		
		if (jugador == null) {
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public Jugador buscarJugadorNickname(String nickname) {
		return jugadorRepositoryJPA.findByNickname(nickname);
	}

	@Override
	public void guardarJugadorSeleccionado(Jugador jugador) {
		jugadorAux = jugador;
	}

	@Override
	public Jugador mostrarJugadorSeleccionado() {
		return jugadorAux;
	}

	@Override
	public void eliminarJugadorSeleccionado() {
		jugadorAux = new Jugador();
	}
	
	

}
