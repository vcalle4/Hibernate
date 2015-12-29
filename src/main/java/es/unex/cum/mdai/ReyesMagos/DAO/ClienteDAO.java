package es.unex.cum.mdai.ReyesMagos.DAO;

import java.util.List;

import es.unex.cum.mdai.ReyesMagosVO.CartaVO;
import es.unex.cum.mdai.ReyesMagosVO.ClienteVO;

public interface ClienteDAO {
	ClienteVO create (ClienteVO p);
	ClienteVO read (int id);
	ClienteVO update (ClienteVO p);
	void delete(ClienteVO p);
	List<ClienteVO> findAll();
	List<CartaVO> cartasToCliente(ClienteVO p);
}
