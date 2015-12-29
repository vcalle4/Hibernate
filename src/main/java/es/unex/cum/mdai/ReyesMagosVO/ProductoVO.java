package es.unex.cum.mdai.ReyesMagosVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Producto")
public class ProductoVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idProducto")
	private int idProducto;
	
	private String nombre;
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "idProveedor")
	private ProveedorVO proveedor;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "idCategoria")
	private CategoriaVO categoria;
	
	@ManyToMany(cascade = { CascadeType.PERSIST })
	@JoinTable(name = "ProductoImagen", joinColumns = { @JoinColumn(name = "idProducto") }, inverseJoinColumns = { @JoinColumn(name = "idImagen") })
	private List<ImagenVO> imagenes = new ArrayList<ImagenVO>();
	
	@OneToMany(mappedBy = "producto", cascade = { CascadeType.ALL})
	private List<ProductoCartaVO> prodCarta= new ArrayList<ProductoCartaVO>();

	public ProductoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductoVO(int idProducto, String nombre, String descripcion,
			ProveedorVO proveedor, CategoriaVO categoria) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.proveedor = proveedor;
		this.categoria=categoria;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ProveedorVO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorVO proveedor) {
		this.proveedor = proveedor;
	}

	public CategoriaVO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaVO categoria) {
		this.categoria = categoria;
	}

	public List<ImagenVO> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<ImagenVO> imagenes) {
		this.imagenes = imagenes;
	}

	public List<ProductoCartaVO> getProdCarta() {
		return prodCarta;
	}

	public void setProdCarta(List<ProductoCartaVO> prodCarta) {
		this.prodCarta = prodCarta;
	}
}
