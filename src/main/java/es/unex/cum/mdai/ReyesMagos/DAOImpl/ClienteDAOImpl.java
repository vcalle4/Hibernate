package es.unex.cum.mdai.ReyesMagos.DAOImpl;

import java.util.List;

import javax.persistence.EntityManager;

import es.unex.cum.mdai.ReyesMagosVO.CartaVO;
import es.unex.cum.mdai.ReyesMagosVO.ClienteVO;
import es.unex.cum.mdai.ReyesMagos.DAO.ClienteDAO;

public class ClienteDAOImpl implements ClienteDAO {
	protected EntityManager entityManager;

	public ClienteDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ClienteVO create(ClienteVO p) {
		this.entityManager.persist(p);
		return p;
	}

	public ClienteVO read(int id) {
		return this.entityManager.find(ClienteVO.class, id);
	}

	public ClienteVO update(ClienteVO p) {
		return this.entityManager.merge(p);
	}

	public void delete(ClienteVO p) {
		p = this.entityManager.merge(p);
		this.entityManager.remove(p);
	}
	
	@SuppressWarnings("unchecked")
	public List<ClienteVO> findAll() {
		return (List<ClienteVO>) this.entityManager.createQuery(
				"from ClienteVO").getResultList();
	}
	
	public List<CartaVO> cartasToCliente(ClienteVO p){
		return (List<CartaVO>)p.getCartas();
	}
}