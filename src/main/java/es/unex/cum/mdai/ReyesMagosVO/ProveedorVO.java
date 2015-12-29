package es.unex.cum.mdai.ReyesMagosVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Proveedor")
public class ProveedorVO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int idProveedor;
	
	private String nombre;
	
	@OneToMany(mappedBy="proveedor",cascade= CascadeType.ALL)
	private List<ProductoVO> productosProv;

	public ProveedorVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProveedorVO(int idProveedor, String nombre) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.productosProv=new ArrayList<ProductoVO>();
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ProductoVO> getProductosProv() {
		return productosProv;
	}

	public void setProductosProv(List<ProductoVO> productosProv) {
		this.productosProv = productosProv;
	}
}
