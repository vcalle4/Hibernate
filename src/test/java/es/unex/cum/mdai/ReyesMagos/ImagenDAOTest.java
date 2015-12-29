package es.unex.cum.mdai.ReyesMagos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unex.cum.mdai.ReyesMagos.DAO.ImagenDAO;
import es.unex.cum.mdai.ReyesMagos.DAOImpl.ImagenDAOImpl;
import es.unex.cum.mdai.ReyesMagosVO.CategoriaVO;
import es.unex.cum.mdai.ReyesMagosVO.ImagenVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;
import es.unex.cum.mdai.ReyesMagosVO.ProveedorVO;

public class ImagenDAOTest {
	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeClass
	// Ir al pom.xml para aumentar el Junit
	public static void createEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("persistencia");
	}

	@AfterClass
	public static void closeEntityManagerFactory() {
		emf.close();
	}

	@Before
	public void beginTransaction() {
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	@After
	public void rollbackTransaction() {
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
		if (em.isOpen()) {
			em.close();
		}
	}
	
	@Test
	public void pruebaImagen() {
		
		//Crear objetos
		ImagenDAO imagenDAO = new ImagenDAOImpl(em);
		ImagenVO imagen = new ImagenVO(1, "playstation.jpg");
		
		ProveedorVO prov = new ProveedorVO(1, "Sony");
		CategoriaVO cat = new CategoriaVO(1, "Videojuegos");
		ProductoVO prod = new ProductoVO (2, "PSP", "Videoconsola portable", prov, cat);
		prod.setCategoria(cat);
		prod.setProveedor(prov);
		imagen.getProductos().add(prod);
		
		// PERSIST
		imagenDAO.create(imagen);
			
		// FIND
		ImagenVO imagen2 = imagenDAO.read(1);
		System.out.println(imagen2.getIdImagen()+" : "+imagen2.getPath());
		
		// UPDATE
		imagen2.setPath("img/playstation.jpg");
		imagenDAO.update(imagen2);
		ImagenVO imagen3 = imagenDAO.read(1);
		System.out.println("Nuevo PATH: "+imagen3.getPath());
		
		// Obtener todas las imagenes
		ArrayList<ImagenVO> imagenes = new ArrayList<ImagenVO>();
		imagenes=(ArrayList<ImagenVO>) imagenDAO.findAll();
		System.out.println("------LISTA DE IMAGENES--------");
		for (int i = 0; i < imagenes.size(); i++) {
			System.out.println(imagenes.get(i).getIdImagen()+": "+imagenes.get(i).getPath());
		}
		
		
		System.out.println("-----LISTA DE PRODUCTOS CON UNA IMAGEN DETERMINADA-----");
		List<ProductoVO> productos = imagenDAO.productosToImagen(imagen);
		for (int i = 0; i < productos.size(); i++) {
			System.out.println(productos.get(i).getNombre());
		}
		
		// REMOVE
		imagenDAO.delete(imagen3);
		ImagenVO imagen4 = imagenDAO.read(1);
		if (imagen4!=null){
			System.out.println("No se ha eliminado: "+imagen.getPath());
		}else{
			System.out.println("Se ha eliminado la imagen");
		}	
		
		em.getTransaction().commit();
		em.close();
	}
}