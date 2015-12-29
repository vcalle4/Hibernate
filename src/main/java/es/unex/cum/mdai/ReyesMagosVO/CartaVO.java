package es.unex.cum.mdai.ReyesMagosVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Carta")
public class CartaVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idCarta")
	private int idCarta;
	
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "idCliente")
	private ClienteVO cliente;
	
	@OneToMany(mappedBy = "carta", cascade = { CascadeType.ALL}, fetch =FetchType.LAZY)
	private List<ProductoCartaVO> ProdCarta= new ArrayList<ProductoCartaVO>();

	public CartaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartaVO(int idCarta, Date fecha, ClienteVO cliente) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
		this.idCarta=idCarta;
	}

	public int getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public List<ProductoCartaVO> getProdCarta() {
		return ProdCarta;
	}

	public void setProdCarta(List<ProductoCartaVO> prodCarta) {
		ProdCarta = prodCarta;
	}
}
