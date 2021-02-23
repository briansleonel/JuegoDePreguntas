/**
 * 
 */
package brians.game.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * Clase que representa una entidad del mundo real, representando un Autor
 * @author González Brian leonel
 *
 */

@Component
@Entity
@Table(name = "AUTOR")
public class Autor {

	/*
	 * ------------ATRIBUTOS -----------
	 */
	
	/**
	 * Atributo que representa el ID de un autor
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	/**
	 * Atributo que representa el Apellido de un autor
	 */
	@Column(name = "APELLIDO")
	private String apellido;
	
	/**
	 * Atributo que representa el Nombre de un autor
	 */
	@Column(name = "NOMBRE")
	private String nombre;
	
	/**
	 * Atributo que permite relacionar un Autor con una Pregunta
	 */
	//@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	//sprivate List<Pregunta> preguntas;
	
	/*
	 * ----------- CONSTRUCTORES --------------
	 */
	
	/**
	 * Constructor sin parametros
	 */
	public Autor() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * ----------- SOBREESRITURA DE TO STRING -------------
	 */

	@Override
	public String toString() {
		return "Autor [ID= " + id + ", Apellido= " + apellido + ", Nombre= " + nombre +"]";
	}



	/*
	 * ----------- METODOS ACCESORES -------------
	 */
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

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

}
