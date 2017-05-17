package beds.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beds.connectionbd.BaseDatos;

public class UsuarioBO {
	public String getUserByUserId(String user, String password){
		Connection con = BaseDatos.conexion();		
		ResultSet rs = null;
		PreparedStatement ps = null;
		String ok = "";
		
		String consulta = "SELECT * FROM usuario WHERE user='"+user+"' AND password='"+password+"'";
		
		 try {
				ps = con.prepareStatement(consulta);				
				rs = ps.executeQuery();
				
				if(rs.next()){
					ok = rs.getString("perfil");
					
				}
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		return ok;
		
	}
	
	public int getIdProveedorByUser(String user)throws Exception{
		Connection con = BaseDatos.conexion();		
		ResultSet rs = null;
		PreparedStatement ps = null;
		String consulta = "SELECT idproveedor FROM proveedor WHERE proveedor.idproveedor IN (SELECT usuario.idrelacion FROM usuario WHERE user='"+user+"')";
		int idproveedor=0;
		 try {
				ps = con.prepareStatement(consulta);				
				rs = ps.executeQuery();
				
				if(rs.next()){
					idproveedor = rs.getInt(1);
					
				}
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		return idproveedor;
	}
}
