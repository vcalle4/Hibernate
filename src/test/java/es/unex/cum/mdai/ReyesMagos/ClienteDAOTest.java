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

import es.unex.cum.mdai.ReyesMagos.DAO.ClienteDAO;
import es.unex.cum.mdai.ReyesMagos.DAOImpl.ClienteDAOImpl;
import es.unex.cum.mdai.ReyesMagosVO.CartaVO;
import es.unex.cum.mdai.ReyesMagosVO.ClienteVO;
import es.unex.cum.mdai.ReyesMagosVO.UsuarioVO;

public class ClienteDAOTest {
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
	public void pruebaCliente() {
		
		//Crear objetos
		UsuarioVO user = new UsuarioVO("vcalle4", "7412", "tipo", new Date(), new Date());	
		ClienteDAO clienteDAO = new ClienteDAOImpl(em);
		ClienteVO cliente = new ClienteVO(1, "Victor", "Calle", "Sanchez", "vc@mail.com", 21, user);
		
		UsuarioVO user1 = new UsuarioVO("manu4", "789645", "tipo", new Date(), new Date());	
		ClienteVO clienteAux = new ClienteVO(2, "Manuel", "Calle", "Sanchez", "manu@mail.com", 21, user1);
		
		cliente.getCartas().add(new CartaVO(1, new Date(2014,11,26),cliente));
		cliente.getCartas().add(new CartaVO(2, new Date(2015,11,26),cliente));
		
		// PERSIST
		clienteDAO.create(cliente);
		clienteDAO.create(clienteAux);
		
		// FIND
		ClienteVO cliente2 = clienteDAO.read(1);
		System.out.println(cliente2.getIdCliente()+" : "+cliente2.getNombre()+" : "+cliente2.getApellido1()+" : "+cliente2.getApellido2()+" : "+cliente2.getEdad()+" : "+cliente2.getEmail()+" : "+cliente2.getUser().getLogin());
		
		// Cartas del cliente
		System.out.println("-----LISTA DE CARTAS DEL CLIENTE-----");
		List<CartaVO> lista = clienteDAO.cartasToCliente(cliente); 
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getIdCarta()+": "+lista.get(i).getFecha());
		}
		
		// UPDATE
		cliente2.setEmail("vcalle@mail.com");
		clienteDAO.update(cliente2);
		ClienteVO cliente3 = clienteDAO.read(1);
		System.out.println("Nuevo email: "+cliente.getEmail());
		
		// Obtener todos los clientes
		ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();
		clientes=(ArrayList<ClienteVO>) clienteDAO.findAll();
		System.out.println("------LISTA DE CLIENTES--------");
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).getNombre());
		}
		
		// REMOVE
		clienteDAO.delete(cliente3);
		ClienteVO cliente4 = clienteDAO.read(1);
		if (cliente4!=null){
			System.out.println("No se ha eliminado: "+cliente4.getNombre());
		}else{
			System.out.println("Se ha eliminado el cliente");
		}	
		
		em.getTransaction().commit();
		em.close();
	}
}