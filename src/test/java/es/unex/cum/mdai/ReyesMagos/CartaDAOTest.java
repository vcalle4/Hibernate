package es.unex.cum.mdai.ReyesMagos;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unex.cum.mdai.ReyesMagos.DAO.CartaDAO;
import es.unex.cum.mdai.ReyesMagos.DAOImpl.CartaDAOImpl;
import es.unex.cum.mdai.ReyesMagosVO.CartaVO;
import es.unex.cum.mdai.ReyesMagosVO.ClienteVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoCartaVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;
import es.unex.cum.mdai.ReyesMagosVO.UsuarioVO;

public class CartaDAOTest {
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
	public void pruebaCarta() {
		
		//Crear objetos
		CartaDAO CartaDAO = new CartaDAOImpl(em);
		CartaVO carta = new CartaVO();
		
		carta.setIdCarta(1);
		carta.setFecha(new Date());
		
		//Asociamos un cliente a la carta
		ClienteVO cliente = new ClienteVO(1, "Victor", "Calle", "Sanchez", "vc@mail.com", 21, new UsuarioVO("vcalle4", "7412", "tipo", new Date(), new Date()));
		carta.setCliente(cliente);
		
		//Añadir productos a la carta
		ProductoVO p = new ProductoVO();
		p.setIdProducto(1);
		p.setNombre("Play Station");
		p.setDescripcion("videoconsola");
		ProductoVO p2 = new ProductoVO();
		p2.setIdProducto(2);
		p2.setNombre("PSP");
		p2.setDescripcion("videoconsola portable");
		
		ProductoCartaVO prodCar= new ProductoCartaVO(p, carta);
		ProductoCartaVO prodCar2= new ProductoCartaVO(p2, carta);
		carta.getProdCarta().add(prodCar);
		carta.getProdCarta().add(prodCar2);
		
		// PERSIST
		CartaDAO.create(carta);
		
		// FIND
		CartaVO carta2 = CartaDAO.read(1);
		System.out.println(carta2.getIdCarta()+" : "+carta2.getFecha());
		
		// UPDATE
		carta2.setFecha(new Date(2015, 7, 15));
		CartaDAO.update(carta2);
		CartaVO carta3 = CartaDAO.read(1);
		System.out.println("Nueva fecha: "+carta3.getFecha());
		
		// Obtener todas las cartas
		ArrayList<CartaVO> cartas = new ArrayList<CartaVO>();
		cartas=(ArrayList<CartaVO>) CartaDAO.findAll();
		System.out.println("------LISTA DE CARTAS--------");
		for (int i = 0; i < cartas.size(); i++) {
			System.out.println(cartas.get(i).getIdCarta());
		}
		
		// Productos de la carta
		ArrayList<ProductoVO> productos = new ArrayList<ProductoVO>();
		productos=(ArrayList<ProductoVO>) CartaDAO.productosToCarta(1);
		System.out.println("------PRODUCTOS DE LA CARTA id=1------");
		for (int i = 0; i < productos.size(); i++) {
			System.out.println(productos.get(i).getNombre());
		}
		
		// REMOVE
		CartaDAO.delete(carta3);
		CartaVO carta4 = CartaDAO.read(1);
		if (carta4!=null){
			System.out.println("No se ha eliminado: "+carta4.getIdCarta());
		}else{
			System.out.println("Se ha eliminado la carta");
		}	
		
		em.getTransaction().commit();
		em.close();
	}
}