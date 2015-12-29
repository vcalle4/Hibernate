package es.unex.cum.mdai.ReyesMagosVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Cliente")
public class ClienteVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int idCliente;
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private int edad;
	
	@OneToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="idUsuario")
	private UsuarioVO user;
	
	@OneToMany(mappedBy="cliente",cascade= CascadeType.ALL)
	private List<CartaVO> cartas;
	
	public ClienteVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClienteVO(int id, String nombre, String apellido1, String apellido2,
			String email, int edad, UsuarioVO user) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.edad = edad;
		this.user=user;
		this.idCliente=id;
		this.cartas = new ArrayList<CartaVO>();
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}

	public UsuarioVO getUser() {
		return user;
	}

	public void setUser(UsuarioVO user) {
		this.user = user;
	}

	public List<CartaVO> getCartas() {
		return cartas;
	}

	public void setCartas(List<CartaVO> cartas) {
		this.cartas = cartas;
	}
}
