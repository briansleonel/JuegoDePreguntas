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
 * Clase que permite representar una entidad del mundo rela de tipo respuesta
 * 
 * @author Gonzalez Brian Leonel
 *
 */

@Component
@Entity
@Table(name = "RESPUESTA")
public class Respuesta {

	/*
	 * -------------- ATRIBUTOS --------------
	 */
	/**
	 * Atributo que representa el ID de una respuesta
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	/**
	 * Atributo que representa la Pregunata fromulada
	 */
	//@Column(name = "PREGUNTA")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PREGUNTA")
	private Pregunta pregunta;
	
	/**
	 * Atributo que representa la Respuesta dada
	 */
	@Column(name = "RESPUESTA")
	private String respuesta;
	
	/**
	 * Atributo que representa si la respuesta es correcta o no
	 * TRUE= correcto; FALSE= incorrecta
	 */
	@Column(name = "CORRECTA")
	private Boolean correcta;
	
	/**
	 * Atributo que representa el registro guardado
	 */
	//@ManyToMany(mappedBy = "respuestas")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_REGISTRO")
	private RegistroJuego registro;
	
	/*
	 * ------------- CONSTRUCTOR ------------
	 */
	/**
	 * Constructor sin parametros
	 */
	public Respuesta() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * ------------ METODO TO STRING ----------------
	 */
	/**
	 * Metodo que permite mostrar los datos de la clase
	 */
	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", pregunta=" + pregunta + ", respuesta=" + respuesta + ", correcta=" + correcta
				+ ", registro=" + registro + "]";
	}

	/*
	 * ------------ METODOS ACCESORES ----------------
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
	public Pregunta getPregunta() {
		return pregunta;
	}

	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the correcta
	 */
	public Boolean getCorrecta() {
		return correcta;
	}

	/**
	 * @param correcta the correcta to set
	 */
	public void setCorrecta(Boolean correcta) {
		this.correcta = correcta;
	}

	/**
	 * @return the registro
	 */
	public RegistroJuego getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(RegistroJuego registro) {
		this.registro = registro;
	}

}
