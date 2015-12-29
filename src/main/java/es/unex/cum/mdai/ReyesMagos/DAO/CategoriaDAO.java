package es.unex.cum.mdai.ReyesMagos.DAO;

import java.util.List;

import es.unex.cum.mdai.ReyesMagosVO.CategoriaVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;


public interface CategoriaDAO {
	CategoriaVO create (CategoriaVO p);
	CategoriaVO read (int id);
	CategoriaVO update (CategoriaVO p);
	void delete(CategoriaVO p);
	List<CategoriaVO> findAll();
	List<ProductoVO> productosToCategoria(CategoriaVO p);
}
