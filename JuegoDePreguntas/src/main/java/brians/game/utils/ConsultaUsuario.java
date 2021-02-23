/**
 * 
 */
package brians.game.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Permite crear un objeto que haga referencia a un objeto de tipo consulta por nombre de usuario
 * 
 * @author Gonzalez Brian Leonel
 *
 */

@Component
public class ConsultaUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa el nombre del usuario
	 */
	private String nombre;

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
