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
@Table(name="Categoria")
public class CategoriaVO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int idCategoria;
	
	private String descripcion;
	
	@OneToMany(mappedBy="categoria",cascade= CascadeType.ALL)
	private List<ProductoVO> productosCat;

	public CategoriaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoriaVO(int idCategoria, String descripcion) {
		super();
		this.idCategoria = idCategoria;
		this.descripcion = descripcion;
		this.productosCat=new ArrayList<ProductoVO>();
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<ProductoVO> getProductosCat() {
		return productosCat;
	}

	public void setProductosCat(List<ProductoVO> productosCat) {
		this.productosCat = productosCat;
	}
}
