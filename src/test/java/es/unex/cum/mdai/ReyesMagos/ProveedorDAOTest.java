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

import es.unex.cum.mdai.ReyesMagos.DAO.ProveedorDAO;
import es.unex.cum.mdai.ReyesMagos.DAOImpl.ProveedorDAOImpl;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;
import es.unex.cum.mdai.ReyesMagosVO.ProveedorVO;

public class ProveedorDAOTest {
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
	public void pruebaProveedor() {
		
		//Crear objetos
		ProveedorDAO provDAO = new ProveedorDAOImpl(em);
		ProveedorVO prov = new ProveedorVO (1, "Sony");
		ProveedorVO provAux = new ProveedorVO (2, "Microsoft");
		
		ProductoVO prod1 = new ProductoVO();
		prod1.setIdProducto(1);
		prod1.setNombre("Play Station 4");
		ProductoVO prod2 = new ProductoVO();
		prod2.setIdProducto(2);
		prod2.setNombre("Sony Xperia");
		ProductoVO prod3 = new ProductoVO();
		prod3.setIdProducto(3);
		prod3.setNombre("Sony TV 4K X94C");
		prov.getProductosProv().add(prod1);
		prov.getProductosProv().add(prod2);
		prov.getProductosProv().add(prod3);
	
		// PERSIST
		provDAO.create(prov);
		provDAO.create(provAux);
		
		// FIND
		ProveedorVO prov2 = provDAO.read(1);
		System.out.println(prov2.getIdProveedor()+" : "+prov2.getNombre());
		
		List<ProductoVO> productos = provDAO.productosToProveedor(prov);
		System.out.println("------LISTA DE PRODUCTOS DEL PROVEEDOR "+prov.getNombre()+"------");
		for (int i = 0; i < productos.size(); i++) {
			System.out.println(productos.get(i).getNombre());
		}
		
		// UPDATE
		prov2.setNombre("Nintendo");
		provDAO.update(prov2);
		ProveedorVO prov3 = provDAO.read(1);
		System.out.println("Nuevo proveedor: "+prov3.getNombre());
		
		// Obtener todos los proveedores
		ArrayList<ProveedorVO> proovedores = new ArrayList<ProveedorVO>();
		proovedores=(ArrayList<ProveedorVO>) provDAO.findAll();
		System.out.println("------LISTA DE PROVEEDORES--------");
		for (int i = 0; i < proovedores.size(); i++) {
			System.out.println(proovedores.get(i).getNombre());
		}
		
		// REMOVE
		provDAO.delete(prov3);
		ProveedorVO prov4 = provDAO.read(1);
		if (prov4!=null){
			System.out.println("No se ha eliminado: "+prov4.getNombre());
		}else{
			System.out.println("Se ha eliminado el proveedor");
		}	
		
		em.getTransaction().commit();
		em.close();
	}
}