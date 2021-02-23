package brians.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brians.game.entity.Autor;
import brians.game.repository.IAutorJPA;

/**
 * Permite implementar la interfaz de AutorService y usar sus servicios
 * 
 * @author Gonzalez Brian Leonel
 *
 */

@Service
public class AutorServiceImp implements IAutorService {
	
	@Autowired
	IAutorJPA autorJPA;
	
	/**
	 * Permite guardar un Autor
	 */
	@Override
	public void guardarAutor(Autor unAutor) {
		autorJPA.save(unAutor);
	}

	/**
	 * Permite listar todos los autores de la BD
	 */
	@Override
	public List<Autor> mostrarAutores() {
		// TODO Auto-generated method stub
		return autorJPA.findAll();
	}

}
