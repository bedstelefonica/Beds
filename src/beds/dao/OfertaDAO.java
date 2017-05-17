package beds.dao;

import java.util.List;

import beds.vo.OfertaVO;

public interface OfertaDAO {
		
	String ESTADO_OFERTA_ABIERTO = "abierta";
	String ESTADO_OFERTA_VALIDADO = "validado"; //cierre postulacion candidatos
	String ESTADO_OFERTA_CERRADO = "cerrado";
	
	public int doCreateOferta(String puesto, String perfil) throws Exception;

	public int doCreateOferta(int idoferta, int idproveedor) throws Exception;

	public List<OfertaVO> doSelectOfertas() throws Exception;
}
