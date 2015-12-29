package es.unex.cum.mdai.ReyesMagos.DAO;

import java.util.List;

import es.unex.cum.mdai.ReyesMagosVO.UsuarioVO;


public interface UsuarioDAO {
	UsuarioVO create (UsuarioVO p);
	UsuarioVO read (int id);
	UsuarioVO update (UsuarioVO p);
	void delete(UsuarioVO p);
	List<UsuarioVO> findAll();
}
