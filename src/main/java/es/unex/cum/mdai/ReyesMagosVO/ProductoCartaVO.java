package es.unex.cum.mdai.ReyesMagosVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProductoCarta")
public class ProductoCartaVO {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idProducto")
	private ProductoVO producto;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idCarta")
	private CartaVO carta;

	public ProductoCartaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductoCartaVO(ProductoVO producto, CartaVO carta) {
		super();
		this.producto = producto;
		this.carta = carta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductoVO getProducto() {
		return producto;
	}

	public void setProducto(ProductoVO producto) {
		this.producto = producto;
	}

	public CartaVO getCarta() {
		return carta;
	}

	public void setCarta(CartaVO carta) {
		this.carta = carta;
	}
}
