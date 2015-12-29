package es.unex.cum.mdai.ReyesMagos.DAO;

import java.util.List;

import es.unex.cum.mdai.ReyesMagosVO.ImagenVO;
import es.unex.cum.mdai.ReyesMagosVO.ProductoVO;


public interface ImagenDAO {
	ImagenVO create (ImagenVO p);
	ImagenVO read (int id);
	ImagenVO update (ImagenVO p);
	void delete(ImagenVO p);
	List<ImagenVO> findAll();
	List<ProductoVO> productosToImagen(ImagenVO p);
}
