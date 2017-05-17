package beds.dao;

import java.util.List;

import beds.vo.ProveedorVO;

public interface ProveedorDAO {

    public List<ProveedorVO> getAllProveedor() throws Exception;	

    public ProveedorVO getProveedorById(long id) throws Exception;	
    
    public List<String> doProveedorOferta(int idoferta) throws Exception;
 
    public int doCreateProveedor(ProveedorVO vo) throws Exception;	

    public int doUpdateProveedor(ProveedorVO vo) throws Exception;
	 
    public int doDeleteProveedor(ProveedorVO vo) throws Exception; 
}
