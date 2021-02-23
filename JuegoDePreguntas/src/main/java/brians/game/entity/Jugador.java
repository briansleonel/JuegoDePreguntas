/**
 * 
 */
package brians.game.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * Clase que permite representar un objeto del mundo real, de tipo jugador.
 * 
 * @author González Brian Leonel
 *
 */

@Component
@Entity
@Table(name = "JUGADOR")
public class Jugador {

	/*
	 * --------------- ATRIBUTOS ---------------
	 */
	
	/**
	 * Atributo que representa el ID de un jugador
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	/**
	 * Atributo que representa el nickname de un jugador
	 */
	@Column(name = "NICKNAME")
	private String nickname;
	
	/**
	 * Atributo que representa el Apellido de un jugador
	 */
	@Column(name = "APELLIDO")
	private String apellido;
	
	/**
	 * Atributo que representa el Nombre de un jugador
	 */
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "ACTIVO")
	private boolean activo;
	
	/**
	 * Atributo que representa el Puntaje de un jugador
	 */
	/*
	@Column(name = "PUNTAJE")
	private Long puntaje; */
	
	/**
	 * Atributo que representa la relacion con un usuario
	 */
	@OneToOne(mappedBy = "jugador")
	private Usuario usuario;
	
	/**
	 * Atributo que representa la relación entre un jugador y sus registros de juegos
	 */
	@OneToMany(mappedBy = "jugador", fetch = FetchType.LAZY)
	private List<RegistroJuego> registros;
	
	/*
	 * -------------- CONSTRUCTORES --------------
	 */
	/**
	 * Constructor sin parametros
	 */
	public Jugador() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * ---------- METODO TO STRING --------------
	 */
	
	/**
	 * Permite devolver los datos almacenados en el objeto
	 */
	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nickname=" + nickname + ", apellido=" + apellido + ", nombre=" + nombre + "]";
	}
	
	/*
	 * ----------- METODOS ACCESORES ---------------
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
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	/**
	 * @return the registros
	 */
	public List<RegistroJuego> getRegistros() {
		return registros;
	}

	/**
	 * @param registros the registros to set
	 */
	public void setRegistros(List<RegistroJuego> registros) {
		this.registros = registros;
	}

	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
