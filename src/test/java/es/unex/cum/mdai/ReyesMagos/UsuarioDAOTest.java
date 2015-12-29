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

import es.unex.cum.mdai.ReyesMagos.DAO.UsuarioDAO;
import es.unex.cum.mdai.ReyesMagos.DAOImpl.UsuarioDAOImpl;
import es.unex.cum.mdai.ReyesMagosVO.UsuarioVO;

public class UsuarioDAOTest {
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
	public void pruebaUsuario() {
		
		//Crear objetos
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		UsuarioVO usuario = new UsuarioVO("vcalle4", "7412", "tipo", new Date(2015,11,25), new Date(2015,11,25));
		UsuarioVO usuarioAux = new UsuarioVO("manuel7", "5871", "tipo", new Date(), new Date());
		
		// PERSIST
		usuarioDAO.create(usuario);
		usuarioDAO.create(usuarioAux);
		
		// FIND
		UsuarioVO usuario2 = usuarioDAO.read(1);
		System.out.println(usuario2.getIdUsuario()+" : "+usuario2.getLogin()+" : "+usuario2.getTipo()+" : "+usuario2.getFecha_alta()+" : "+usuario2.getFecha_ultimo_acceso());
		
		// UPDATE
		usuario2.setFecha_ultimo_acceso(new Date());
		usuarioDAO.update(usuario2);
		UsuarioVO usuario3 = usuarioDAO.read(1);
		System.out.println("Nueva fecha del último acceso: "+usuario3.getFecha_ultimo_acceso());
		
		// Obtener todos los usuarios
		ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		usuarios=(ArrayList<UsuarioVO>) usuarioDAO.findAll();
		System.out.println("------LISTA DE USUARIOS--------");
		for (int i = 0; i < usuarios.size(); i++) {
			System.out.println(usuarios.get(i).getIdUsuario()+": "+usuarios.get(i).getLogin());
		}
		
		// REMOVE
		usuarioDAO.delete(usuarioAux);
		UsuarioVO usuario4 = usuarioDAO.read(2);
		if (usuario4!=null){
			System.out.println("No se ha eliminado: "+usuario4.getLogin());
		}else{
			System.out.println("Se ha eliminado el usuario");
		}	
		
		em.getTransaction().commit();
		em.close();
	}
}