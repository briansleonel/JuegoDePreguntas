/**
 * 
 */
package brians.game.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brians.game.entity.Categoria;
import brians.game.entity.Pregunta;
import brians.game.entity.Usuario;
import brians.game.repository.IPreguntaRepositoryJPA;
import brians.game.repository.IUsuarioRepositoryJPA;
import brians.game.utils.NumerosAleatorios;

/**
 * @author Gonzalez Brian Leonel
 *
 */

@Service
public class PreguntaServiceImp implements IPreguntaService {
	
	@Autowired
	private IPreguntaRepositoryJPA preguntaRepositoryJPA;
	
	@Autowired 
	private IUsuarioRepositoryJPA usuarioRepositoryJPA;
	
	//@Autowired
	private Categoria categoria;
	
	@Autowired
	NumerosAleatorios numerosAleatorios;

	@Override
	public void guardarPregunta(Pregunta unaPregunta) {
		preguntaRepositoryJPA.save(unaPregunta);
	}

	@Override
	public void eliminarPregunta(Long id) {
		preguntaRepositoryJPA.deleteById(id);
	}

	@Override
	public List<Pregunta> mostrarPreguntas() {
		return preguntaRepositoryJPA.findAll();
	}

	@Override
	public List<Pregunta> buscarPreguntasPorUsuario(String username) {
		Usuario usuarioEnc = usuarioRepositoryJPA.findAllByUsername(username);
		return preguntaRepositoryJPA.findByUsuario(usuarioEnc);
	}

	@Override
	public Pregunta extraerPregunta(Long id) {
		return preguntaRepositoryJPA.findById(id).get();
	}
	
	public void almacenarCategoriaSeleccionada(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public void vaciarCategoriaSeleccionada() {
		categoria = new Categoria();
		
	}

	@Override
	public List<Pregunta> buscarPreguntasPorCategoria(Categoria categoria) {
		
		List<Pregunta> preguntasSelect = new ArrayList<Pregunta>();
		List<Pregunta> listAux = new ArrayList<Pregunta>();
		List<Integer> numerosRandom = new ArrayList<Integer>();
		
		if(categoria.getNombre().equals("ALEATORIO")) {
			
			listAux = preguntaRepositoryJPA.findAll();
			numerosRandom = numerosAleatorios.numerosAleatorios(0, listAux.size() - 1);
			
			for(int i=0; i<10; i++) {
				
				preguntasSelect.add(listAux.get(numerosRandom.get(i)));
			}
			
		} else {
			
			listAux = preguntaRepositoryJPA.findByCategoria(categoria);
			
			numerosRandom = numerosAleatorios.numerosAleatorios(0, listAux.size()-1);
			
			for(int i=0; i<10; i++) {
				
				preguntasSelect.add(listAux.get(numerosRandom.get(i)));
			}
			
		}
		
		return preguntasSelect;
	}

}
