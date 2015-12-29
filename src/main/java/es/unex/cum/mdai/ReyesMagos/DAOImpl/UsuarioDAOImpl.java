package es.unex.cum.mdai.ReyesMagos.DAOImpl;

import java.util.List;

import javax.persistence.EntityManager;

import es.unex.cum.mdai.ReyesMagosVO.UsuarioVO;
import es.unex.cum.mdai.ReyesMagos.DAO.UsuarioDAO;

public class UsuarioDAOImpl implements UsuarioDAO {
	protected EntityManager entityManager;

	public UsuarioDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public UsuarioVO create(UsuarioVO p) {
		this.entityManager.persist(p);
		return p;
	}

	public UsuarioVO read(int id) {
		return this.entityManager.find(UsuarioVO.class, id);
	}

	public UsuarioVO update(UsuarioVO p) {
		return this.entityManager.merge(p);
	}

	public void delete(UsuarioVO p) {
		p = this.entityManager.merge(p);
		this.entityManager.remove(p);
	}
	@SuppressWarnings("unchecked")
	public List<UsuarioVO> findAll() {
		return (List<UsuarioVO>) this.entityManager.createQuery(
				"from UsuarioVO").getResultList();
	}
}