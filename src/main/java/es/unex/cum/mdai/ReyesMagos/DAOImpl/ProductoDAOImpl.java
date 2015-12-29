package es.unex.cum.mdai.ReyesMagos.DAOImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.unex.cum.mdai.ReyesMagosVO.CartaVO;
import es.unex.cum.mdai.ReyesMagosVO.ImagenVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;
import es.unex.cum.mdai.ReyesMagos.DAO.ProductoDAO;

public class ProductoDAOImpl implements ProductoDAO {
	protected EntityManager entityManager;

	public ProductoDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ProductoVO create(ProductoVO p) {
		this.entityManager.persist(p);
		return p;
	}

	public ProductoVO read(int id) {
		return this.entityManager.find(ProductoVO.class, id);
	}

	public ProductoVO update(ProductoVO p) {
		return this.entityManager.merge(p);
	}

	public void delete(ProductoVO p) {
		p = this.entityManager.merge(p);
		this.entityManager.remove(p);
	}
	@SuppressWarnings("unchecked")
	public List<ProductoVO> findAll() {
		return (List<ProductoVO>) this.entityManager.createQuery(
				"from ProductoVO").getResultList();
	}
	
	//Cartas que contienen un producto
    @SuppressWarnings("unchecked")
	public List<CartaVO> cartasToProducto(int id) {
        String queryString = "SELECT DISTINCT c FROM CartaVO c"
                + " INNER JOIN c.ProdCarta t WHERE t.producto.id=:x";

        Query query = this.entityManager.createQuery(queryString);

        query.setParameter("x", id);

        List<CartaVO> resultList = (List<CartaVO>) query.getResultList();
        return resultList;

    }

    //Imagenes de un producto
	public List<ImagenVO> imagenesToProducto(ProductoVO p) {
		return (List<ImagenVO>)p.getImagenes();
	}
}