package es.unex.cum.mdai.ReyesMagos.DAOImpl;

import java.util.List;

import javax.persistence.EntityManager;

import es.unex.cum.mdai.ReyesMagosVO.CategoriaVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;
import es.unex.cum.mdai.ReyesMagos.DAO.CategoriaDAO;

public class CategoriaDAOImpl implements CategoriaDAO {
	protected EntityManager entityManager;

	public CategoriaDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public CategoriaVO create(CategoriaVO p) {
		this.entityManager.persist(p);
		return p;
	}

	public CategoriaVO read(int id) {
		return this.entityManager.find(CategoriaVO.class, id);
	}

	public CategoriaVO update(CategoriaVO p) {
		return this.entityManager.merge(p);
	}

	public void delete(CategoriaVO p) {
		p = this.entityManager.merge(p);
		this.entityManager.remove(p);
	}
	@SuppressWarnings("unchecked")
	public List<CategoriaVO> findAll() {
		return (List<CategoriaVO>) this.entityManager.createQuery(
				"from CategoriaVO").getResultList();
	}

	// Productos de la categoria
	public List<ProductoVO> productosToCategoria(CategoriaVO p) {
		return (List<ProductoVO>) p.getProductosCat();
	}
	
}