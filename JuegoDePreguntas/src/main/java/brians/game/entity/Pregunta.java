/**
 * 
 */
package brians.game.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * Clase que permite representar una entidad del mundo real de tipo Pregunta.
 * 
 * @author González Brian Leonel
 *
 */

@Component
@Entity
@Table(name = "PREGUNTA")
public class Pregunta {

	/*
	 * ------------ ATRIBUTOS -------------------
	 */
	
	/**
	 * Atributo que representa el ID de una pregunta
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	/**
	 * Atributo que representa la Pregunta formulada
	 */
	@Column(name = "PREGUNTA")
	private String pregunta;
	
	/**
	 * Atributo que representa la Respuesta Correcta
	 */
	@Column(name = "RESPUESTA_CORRECTA")
	private String correcta;
	
	/**
	 * Atributo que representa la Opcion 1
	 */
	@Column(name = "OPCION_1")
	private String opcion1;
	
	/**
	 * Atributo que representa la Opcion 2
	 */
	@Column(name = "OPCION_2")
	private String opcion2;
	
	/**
	 * Atributo que representa la Opcion 3
	 */
	@Column(name = "OPCION_3")
	private String opcion3;
	
	/**
	 * Atributo que representa la Categoria de una pregunta
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;
	
	/**
	 * Atributo que representa el estado de una pregunta
	 */
	@Column(name = "ESTADO")
	private boolean estado;
	/*
	@ManyToMany
	@Column(name = "CATEGORIA")
	private List<Categoria> categoria; */
	
	/**
	 * Atributo que representa el Autor de la pregunta
	 */
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "ID_AUTOR")
	//private Autor autor;
	
	/**
	 * Atributo que representa el Usuario Autor de una pregunta
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	/**
	 * Atributo que representa las respuestas posibles de otros jugadores
	 */
	/*
	@OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
	private List<Respuesta> respuestas; */
	
	/*
	 * ----------- CONSTRUCTORES ----------------
	 */
	/**
	 * Constructor sin parametros
	 */
	public Pregunta() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * --------------- METODO TO STRING --------------
	 */
	/**
	 * Metodo que permite mostrar los datos almacenados en la clase
	 */
	@Override
	public String toString() {
		return "Pregunta [id=" + id + ", pregunta=" + pregunta + ", correcta=" + correcta
				+ ", opcion1=" + opcion1 + ", opcion2=" + opcion2 + ", opcion3=" + opcion3 + ", categoria=" + categoria
				+ "]";
	}
	
	/*
	 * --------------- METODOS ACCESORES --------------
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
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}

	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * @return the correcta
	 */
	public String getCorrecta() {
		return correcta;
	}

	/**
	 * @param correcta the correcta to set
	 */
	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}

	/**
	 * @return the opcion1
	 */
	public String getOpcion1() {
		return opcion1;
	}

	/**
	 * @param opcion1 the opcion1 to set
	 */
	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	/**
	 * @return the opcion2
	 */
	public String getOpcion2() {
		return opcion2;
	}

	/**
	 * @param opcion2 the opcion2 to set
	 */
	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	/**
	 * @return the opcion3
	 */
	public String getOpcion3() {
		return opcion3;
	}

	/**
	 * @param opcion3 the opcion3 to set
	 */
	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
