package brians.game.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * Clase que permite representar una entidad del mundo real del tipo registro de un juego
 * 
 * @author González Brian Leonel
 *
 */

@Component
@Entity
@Table(name = "REGISTRO_JUEGO")
public class RegistroJuego {

	/*
	 * -------------- ATRIBUTOS ----------------
	 */
	
	/**
	 * Atributo que representa el ID del registro de un juego
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	/**
	 * Atributo que representa la fecha del registro
	 */
	@Column(name = "FECHA")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime fecha;
	
	/**
	 * Atributo que representa el jugador que participó
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_JUGADOR")
	private Jugador jugador;
	
	/**
	 * Atributo que representa el puntaje obtenido en el juego
	 */
	@Column(name = "PUNTAJE")
	private Long puntaje;
	
	/**
	 * Atributo que representa los aciertos en una pregunta
	 * Ej: 7/10
	 */
	@Column(name = "ACIERTOS")
	private String aciertos;
	
	/**
	 * Atributo que permite representar el listado de respuestas del jugador
	 */
	//@ManyToMany
	//@JoinTable(name = "REGISTRO_RESPUESTA", joinColumns = @JoinColumn(name ="REGISTRO_ID"), inverseJoinColumns = @JoinColumn(name = "RESPUESTA_ID"))
	@OneToMany(mappedBy = "registro", cascade = CascadeType.ALL)
	private List<Respuesta> respuestas;
	
	/*
	 * ------------ CONSTRUCTORES -------------
	 */
	
	/**
	 * Constructor sin parametros
	 */
	public RegistroJuego() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * ------------- METODO TO STRING ---------------
	 */
	/**
	 * Metodo que permite devolver los datos almacenados en la clase
	 */
	@Override
	public String toString() {
		return "RegistroJuego [id=" + id + ", fecha=" + fecha + ", jugador=" + jugador + ", puntaje=" + puntaje
				+ ", aciertos=" + aciertos + ", respuestas=" + respuestas + "]";
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
	 * @return the fecha
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the jugador
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * @param jugador the jugador to set
	 */
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	/**
	 * @return the puntaje
	 */
	public Long getPuntaje() {
		return puntaje;
	}

	/**
	 * @param puntaje the puntaje to set
	 */
	public void setPuntaje(Long puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * @return the respuestas
	 */
	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	/**
	 * @param respuestas the respuestas to set
	 */
	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	/**
	 * @return the aciertos
	 */
	public String getAciertos() {
		return aciertos;
	}

	/**
	 * @param aciertos the aciertos to set
	 */
	public void setAciertos(String aciertos) {
		this.aciertos = aciertos;
	}

}
