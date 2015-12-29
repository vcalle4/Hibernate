package es.unex.cum.mdai.ReyesMagos.DAOImpl;

import java.util.List;

import javax.persistence.EntityManager;

import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;
import es.unex.cum.mdai.ReyesMagosVO.ProveedorVO;
import es.unex.cum.mdai.ReyesMagos.DAO.ProveedorDAO;

public class ProveedorDAOImpl implements ProveedorDAO {
	protected EntityManager entityManager;

	public ProveedorDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ProveedorVO create(ProveedorVO p) {
		this.entityManager.persist(p);
		return p;
	}

	public ProveedorVO read(int id) {
		return this.entityManager.find(ProveedorVO.class, id);
	}

	public ProveedorVO update(ProveedorVO p) {
		return this.entityManager.merge(p);
	}

	public void delete(ProveedorVO p) {
		p = this.entityManager.merge(p);
		this.entityManager.remove(p);
	}
	@SuppressWarnings("unchecked")
	public List<ProveedorVO> findAll() {
		return (List<ProveedorVO>) this.entityManager.createQuery(
				"from ProveedorVO").getResultList();
	}

	//Lista de productos de un proveedor
	public List<ProductoVO> productosToProveedor(ProveedorVO p) {
		return (List<ProductoVO>)p.getProductosProv();
	}
}