package es.unex.cum.mdai.ReyesMagosVO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class UsuarioVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idUsuario")
	private int idUsuario;
	
	private String login;
	private String password;
	private String tipo;
	private Date fecha_alta;
	private Date fecha_ultimo_acceso;
	
	public UsuarioVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioVO(String login, String password,
			String tipo, Date fecha_alta, Date fecha_ultimo_acceso) {
		super();
		this.login = login;
		this.password = password;
		this.tipo = tipo;
		this.fecha_alta = fecha_alta;
		this.fecha_ultimo_acceso = fecha_ultimo_acceso;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Date getFecha_ultimo_acceso() {
		return fecha_ultimo_acceso;
	}

	public void setFecha_ultimo_acceso(Date fecha_ultimo_acceso) {
		this.fecha_ultimo_acceso = fecha_ultimo_acceso;
	}
}
