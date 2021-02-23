/**
 * 
 */
package brians.game.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * Clase que representa una entidad de tipo categoría de pregunta
 * 
 * @author González Brian leonel
 *
 */

@Component
@Entity
@Table(name = "CATEGORIA")
public class Categoria {

	/*
	 * --------------- ATRIBUTOS ----------------
	 */
	
	/**
	 * Atributo que representa el ID de una categoria
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	/**
	 * Atributo que representa el nombre de la categoria
	 */
	@Column(name = "NOMBRE")
	private String nombre;
	
	/**
	 * Atributo que permite relacionar una Pregunta con una Categoria
	 */
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<Pregunta> preguntas;
	
	/*
	 * ------------- CONSTRUCTORES ---------------
	 */
	/**
	 * Constructor sin parámetros
	 */
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * --------------- METODO TO STRING ------------------
	 */
	/**
	 * Metodo que permite mostrar los valores almacenados en la clase
	 */
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}


	/*
	 * ------------ METODOS ACCESORES --------------
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
