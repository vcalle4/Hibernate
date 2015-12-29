package es.unex.cum.mdai.ReyesMagos.DAOImpl;

import java.util.List;

import javax.persistence.EntityManager;

import es.unex.cum.mdai.ReyesMagosVO.ImagenVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;
import es.unex.cum.mdai.ReyesMagos.DAO.ImagenDAO;

public class ImagenDAOImpl implements ImagenDAO {
	protected EntityManager entityManager;

	public ImagenDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ImagenVO create(ImagenVO p) {
		this.entityManager.persist(p);
		return p;
	}

	public ImagenVO read(int id) {
		return this.entityManager.find(ImagenVO.class, id);
	}

	public ImagenVO update(ImagenVO p) {
		return this.entityManager.merge(p);
	}

	public void delete(ImagenVO p) {
		p = this.entityManager.merge(p);
		this.entityManager.remove(p);
	}
	@SuppressWarnings("unchecked")
	public List<ImagenVO> findAll() {
		return (List<ImagenVO>) this.entityManager.createQuery(
				"from ImagenVO").getResultList();
	}

	// PRODUCTOS CON UNA IMAGEN
	public List<ProductoVO> productosToImagen(ImagenVO p) {
		return (List<ProductoVO>) p.getProductos();
	}
}
