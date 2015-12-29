package es.unex.cum.mdai.ReyesMagos.DAO;

import java.util.List;

import es.unex.cum.mdai.ReyesMagosVO.CartaVO;
import es.unex.cum.mdai.ReyesMagosVO.ImagenVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;

public interface ProductoDAO {
	ProductoVO create (ProductoVO p);
	ProductoVO read (int id);
	ProductoVO update (ProductoVO p);
	void delete(ProductoVO p);
	List<ProductoVO> findAll();
	List<CartaVO> cartasToProducto(int id);
	List<ImagenVO> imagenesToProducto(ProductoVO p);
}
