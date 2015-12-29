package es.unex.cum.mdai.ReyesMagos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unex.cum.mdai.ReyesMagos.DAO.ProductoDAO;
import es.unex.cum.mdai.ReyesMagos.DAOImpl.ProductoDAOImpl;
import es.unex.cum.mdai.ReyesMagosVO.CartaVO;
import es.unex.cum.mdai.ReyesMagosVO.CategoriaVO;
import es.unex.cum.mdai.ReyesMagosVO.ImagenVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoCartaVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;
import es.unex.cum.mdai.ReyesMagosVO.ProveedorVO;

public class ProductoDAOTest {
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

	@SuppressWarnings("deprecation")
	@Test
	public void pruebaProducto() {
		
		//Crear objetos
		ProductoDAO ProductoDAO = new ProductoDAOImpl(em);
		ProductoVO Producto = new ProductoVO();
		
		Producto.setIdProducto(1);
		Producto.setNombre("Play Station");
		Producto.setDescripcion("videoconsola");
		
		ImagenVO img1 = new ImagenVO(1, "ps3.jpg");
		ImagenVO img2 = new ImagenVO(2, "mando.jpg");
		ImagenVO img3 = new ImagenVO(3, "ps3back.jpg");
		Producto.getImagenes().add(img1);
		Producto.getImagenes().add(img2);
		Producto.getImagenes().add(img3);
		
		ProveedorVO prov = new ProveedorVO(1, "Sony");
		CategoriaVO cat = new CategoriaVO(1, "Videojuegos");
		
		Producto.setCategoria(cat);
		Producto.setProveedor(prov);
		
		CartaVO carta = new CartaVO();
		carta.setIdCarta(1);
		carta.setFecha(new Date(2014,11,25));
		
		CartaVO carta2 = new CartaVO();
		carta2.setIdCarta(2);
		carta2.setFecha(new Date());
		
		ProductoCartaVO prodCar= new ProductoCartaVO(Producto, carta);
		ProductoCartaVO prodCar2= new ProductoCartaVO(Producto, carta2);
		Producto.getProdCarta().add(prodCar);
		Producto.getProdCarta().add(prodCar2);
		
		// PERSIST
		ProductoDAO.create(Producto);
		
		// FIND
		ProductoVO Producto2 = ProductoDAO.read(1);
		System.out.println(Producto2.getIdProducto()+" : "+Producto2.getNombre()+" : "+Producto2.getDescripcion());
		
		// UPDATE
		Producto2.setDescripcion("versión número 4 de la videoconsola");
		ProductoDAO.update(Producto2);
		ProductoVO Producto3 = ProductoDAO.read(1);
		System.out.println("Nueva descripción: "+Producto3.getDescripcion());
		
		// Obtener todos las productos
		ArrayList<ProductoVO> productos = new ArrayList<ProductoVO>();
		productos=(ArrayList<ProductoVO>) ProductoDAO.findAll();
		System.out.println("------LISTA DE PRODUCTOS--------");
		for (int i = 0; i < productos.size(); i++) {
			System.out.println(productos.get(i).getIdProducto()+": "+productos.get(i).getNombre());
		}
		
		// Cartas que contienen un producto
		ArrayList<CartaVO> cartas = new ArrayList<CartaVO>();
		cartas=(ArrayList<CartaVO>) ProductoDAO.cartasToProducto(1);
		System.out.println("------CARTAS CON EL PRODUCTO id=1------");
		for (int i = 0; i < cartas.size(); i++) {
			System.out.println(cartas.get(i).getIdCarta()+" : "+cartas.get(i).getFecha());
		}
		
		
		System.out.println("------IMAGENES DEL PRODUCTO------");
		List<ImagenVO> imagenes = ProductoDAO.imagenesToProducto(Producto);
		for (int i = 0; i < imagenes.size(); i++) {
			System.out.println(imagenes.get(i).getPath());
		}
		
		// REMOVE
		ProductoDAO.delete(Producto3);
		ProductoVO Producto4 = ProductoDAO.read(1);
		if (Producto4!=null){
			System.out.println("No se ha eliminado: "+Producto4.getIdProducto());
		}else{
			System.out.println("Se ha eliminado el producto");
		}
		
		em.getTransaction().commit();
		em.close();
	}
}