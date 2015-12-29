package es.unex.cum.mdai.ReyesMagos.DAO;

import java.util.List;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;
import es.unex.cum.mdai.ReyesMagosVO.ProveedorVO;


public interface ProveedorDAO {
	ProveedorVO create (ProveedorVO p);
	ProveedorVO read (int id);
	ProveedorVO update (ProveedorVO p);
	void delete(ProveedorVO p);
	List<ProveedorVO> findAll();
	List<ProductoVO> productosToProveedor(ProveedorVO p);
}
