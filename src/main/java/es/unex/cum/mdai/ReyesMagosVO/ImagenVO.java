package es.unex.cum.mdai.ReyesMagosVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Imagen")
public class ImagenVO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idImagen")
	private int idImagen;
	
	private String path;
	
	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "imagenes")
	private List<ProductoVO> productos = new ArrayList<ProductoVO>();

	public ImagenVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImagenVO(int idImagen, String path) {
		super();
		this.idImagen = idImagen;
		this.path = path;
	}

	public int getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<ProductoVO> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoVO> productos) {
		this.productos = productos;
	}
}
