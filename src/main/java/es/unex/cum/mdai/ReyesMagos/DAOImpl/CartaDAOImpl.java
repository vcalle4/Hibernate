package es.unex.cum.mdai.ReyesMagos.DAOImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.unex.cum.mdai.ReyesMagosVO.CartaVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;
import es.unex.cum.mdai.ReyesMagos.DAO.CartaDAO;


public class CartaDAOImpl implements CartaDAO {
	protected EntityManager entityManager;

	public CartaDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public CartaVO create(CartaVO p) {
		this.entityManager.persist(p);
		return p;
	}

	public CartaVO read(int id) {
		return this.entityManager.find(CartaVO.class, id);
	}

	public CartaVO update(CartaVO p) {
		return this.entityManager.merge(p);
	}

	public void delete(CartaVO p) {
		p = this.entityManager.merge(p);
		this.entityManager.remove(p);
	}
	@SuppressWarnings("unchecked")
	public List<CartaVO> findAll() {
		return (List<CartaVO>) this.entityManager.createQuery(
				"from CartaVO").getResultList();
	}
	
	//Productos de una carta
    @SuppressWarnings("unchecked")
	public List<ProductoVO> productosToCarta(int id) {
        String queryString = "SELECT DISTINCT c FROM ProductoVO c"
                + " INNER JOIN c.prodCarta t WHERE t.carta.id=:x";

        Query query = this.entityManager.createQuery(queryString);

        query.setParameter("x", id);

        List<ProductoVO> resultList = (List<ProductoVO>) query.getResultList();
        return resultList;

    }
}