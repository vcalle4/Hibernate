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

import es.unex.cum.mdai.ReyesMagos.DAO.CategoriaDAO;
import es.unex.cum.mdai.ReyesMagos.DAOImpl.CategoriaDAOImpl;
import es.unex.cum.mdai.ReyesMagosVO.CategoriaVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;

public class CategoriaDAOTest {
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
	public void pruebaCategoria() {
		
		//Crear objetos
		CategoriaDAO catDAO = new CategoriaDAOImpl(em);
		CategoriaVO cat = new CategoriaVO (1, "videojuegos de PS4");
		CategoriaVO catAux = new CategoriaVO (2, "videojuegos de Xbox One");
		
		ProductoVO prod1 = new ProductoVO();
		prod1.setIdProducto(1);
		prod1.setNombre("Madden NFL 16");
		ProductoVO prod2 = new ProductoVO();
		prod2.setIdProducto(2);
		prod2.setNombre("NBA 2k16");
		ProductoVO prod3 = new ProductoVO();
		prod3.setIdProducto(3);
		prod3.setNombre("Gran Turismo 4");
		cat.getProductosCat().add(prod1);
		cat.getProductosCat().add(prod2);
		cat.getProductosCat().add(prod3);
		
		// PERSIST
		catDAO.create(cat);
		catDAO.create(catAux);
		
		// FIND
		CategoriaVO cat2 = catDAO.read(1);
		System.out.println(cat2.getIdCategoria()+" : "+cat2.getDescripcion());
		
		// UPDATE
		cat2.setDescripcion("videojuegos de PS3");
		catDAO.update(cat2);
		CategoriaVO cat3 = catDAO.read(1);
		System.out.println("Nueva descripción: "+cat3.getDescripcion());
		
		// Obtener todas los categorías
		ArrayList<CategoriaVO> categorias = new ArrayList<CategoriaVO>();
		categorias=(ArrayList<CategoriaVO>) catDAO.findAll();
		System.out.println("------LISTA DE CATEGORÍAS--------");
		for (int i = 0; i < categorias.size(); i++) {
			System.out.println(categorias.get(i).getDescripcion());
		}
		
		
		System.out.println("PRODUCTOS EN LA CATEGORÍA "+cat.getDescripcion());
		List<ProductoVO> productos = catDAO.productosToCategoria(cat);
		for (int i = 0; i < productos.size(); i++) {
			System.out.println(productos.get(i).getNombre());
		}
		
		// REMOVE
		catDAO.delete(cat3);
		CategoriaVO cat4 = catDAO.read(1);
		if (cat4!=null){
			System.out.println("No se ha eliminado: "+cat4.getDescripcion());
		}else{
			System.out.println("Se ha eliminado la categoria");
		}	
		
		em.getTransaction().commit();
		em.close();
	}
}