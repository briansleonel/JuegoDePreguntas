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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

/**
 * Clase que representa una entidad del mundo real, un objeto de tipo Usuario
 * 
 * @author González Brian Leonel
 *
 */

@Component
@Entity
@Table(name = "USUARIO")
public class Usuario {

	/*
	 * ------------ ATRIBUTOS -----------
	 */
	
	/**
	 * Atributo que representa el ID de un usuario
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	/**
	 * Atributo que representa el Nombre de Usuario
	 */
	@Column(name = "USERNAME")
	@NotBlank(message = "¡ATENCIÓN! Debe ingresar un Nombre de Usuario")
	private String username;
	
	/**
	 * Atributo que representa el Password de un usuario
	 */
	@Column(name = "PASSWORD")
	@NotBlank(message = "¡ATENCIÓN! Debe ingresar su Contraseña")
	@Size(min = 8, message = "¡ATENCIÓN! Su contraseña debe tener como mínimo 8 caracteres")
	private String password;
	
	/**
	 * Atributo que representa el Apellido de un usuario
	 */
	@Column(name = "APELLIDO")
	@NotBlank(message = "¡ATENCIÓN! Debe ingresar su Apellido")
	private String apellido;
	
	/**
	 * Atributo que representa el Nombre de un usuario
	 */
	@Column(name = "NOMBRE")
	@NotBlank(message = "¡ATENCIÓN! Debe ingresar su Nombre")
	private String nombre;
	
	/**
	 * Atributo que representa el Email de un usuario
	 */
	@Column(name = "EMAIL")
	@NotBlank(message = "¡ATENCIÓN! Debe ingresar su Correo Electrónico")
	@Email
	private String email;
	
	/**
	 * Atributo que representa el tipo de usuario: ADMIN, AUTOR
	 */
	@Column(name = "TIPO")
	private String tipo;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_JUGADOR")
	private Jugador jugador;
	
	/**
	 * Atributo que representa las preguntas de un usuario
	 */
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Pregunta> preguntas;

	/*
	 * ------------- CONSTRUCTORES --------------
	 */
	
	/**
	 * Constructor sin parametros
	 */
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * ------------ METODO TO STRING ---------------
	 */
	
	/**
	 * Permite mostrar los datos almacenados en el objeto
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", apellido=" + apellido
				+ ", nombre=" + nombre + ", email=" + email + ", tipo=" + tipo + "]";
	}

	/*
	 * ------------ METODOS ACCESORES ---------------
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
	public String getUsername() {
		return username;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * @return the preguntas
	 */
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	/**
	 * @param preguntas the preguntas to set
	 */
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
}
