package beds.dao;

import java.util.List;
/*
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;*/

import beds.vo.UsuarioVO;

public interface UsuarioDAO {
	/*
	    String MQL_GET_ALL_USUARIO = "select * from usuario";
		
	    String MQL_GET_USUARIO_BY_ID_USER = "select * from usuario where idusuario = #{id}";
	
	    String MQL_CREATE_USUARIO = "insert into usuario (user, password, profile, name, surname) values (#{user},#{password},#{profie},#{name},#{surname})";
	
	    String MQL_UPDATE_USUARIO = "update usuario set name=#{name}, surname=#{name} where user=#{user}";
	    
	    String MQL_UPDATE_PASSWORD = "update usuario set password=#{password} where user=#{user}";
	
	    String MQL_DELETE_USUARIO = "delete from usuario where idproducto=#{id}";	
	 */
		
		String PERFIL_BACKOFFICE = "back";
		String PERFIL_FRONTOFFICE= "front";
	
	  //  @Select(MQL_GET_ALL_USUARIO)	
	    public List<UsuarioVO> getAllUser() throws Exception;	
	 
	
	   // @Select(MQL_GET_USUARIO_BY_ID_USER)	
	    public UsuarioVO getUserById(long id) throws Exception;
	    public String getUserByUserId (String user, String password) throws Exception;
	    
	   // public UsuarioVO getUserByUserId(String user, String password) throws Exception;	
	 
	    public int getIdProveedorByUser(String user)throws Exception;
	
	 //   @Insert(MQL_CREATE_USUARIO)	
	    public int doCreateUser(UsuarioVO vo) throws Exception;	
	 
	
	//    @Update(MQL_UPDATE_USUARIO)	
	    public int doUpdateUser(UsuarioVO vo) throws Exception;
		 
	 //   @Update(MQL_UPDATE_PASSWORD)	
	    public int doUpdatePassword(UsuarioVO vo) throws Exception;
	    
	 //   @Delete(MQL_DELETE_USUARIO)	
	    public int doDeleteUser(UsuarioVO vo) throws Exception; 
}
