package es.unex.cum.mdai.ReyesMagos.DAO;

import java.util.List;

import es.unex.cum.mdai.ReyesMagosVO.CartaVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;


public interface CartaDAO {
	CartaVO create (CartaVO p);
	CartaVO read (int id);
	CartaVO update (CartaVO p);
	void delete(CartaVO p);
	List<CartaVO> findAll();
	List<ProductoVO> productosToCarta(int id);
}
