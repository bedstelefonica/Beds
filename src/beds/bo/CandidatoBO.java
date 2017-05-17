package beds.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beds.connectionbd.BaseDatos;
import beds.vo.CandidatoVO;

public class CandidatoBO {

	public List <CandidatoVO> doGetCandidatosByProvOferta(int idoferta, int idproveedor){
		Connection con = BaseDatos.conexion();	
		ResultSet rs = null;
		CandidatoVO cvo = new CandidatoVO();
		List <CandidatoVO> candidatos = new ArrayList<CandidatoVO>();
		PreparedStatement ps = null;
		String consulta = "SELECT nombrecandidato, idcandidato FROM candidato WHERE idoferta="+idoferta+" AND idproveedor="+idproveedor;
		
		try{
			ps = con.prepareStatement(consulta);				
			rs = ps.executeQuery();
			
			while(rs.next()){
				cvo = new CandidatoVO();
				cvo.setIdcandidato(rs.getInt("idcandidato"));
				cvo.setNombrecandidato(rs.getString("nombrecandidato"));
				cvo.setIdoferta(idoferta);
				cvo.setIdproveedor(idproveedor);				
				candidatos.add(cvo);
			}
			
			rs.close();
		ps.close();
		con.close();
	} catch (SQLException e) {
	
		e.printStackTrace();
	} 
		return candidatos;		
	}
	public int doSetCandidatoProveedor(String candidato, int idoferta, int idproveedor){
		Connection con = BaseDatos.conexion();	
		
		int valor = 0;
		PreparedStatement ps = null;
		String consulta = "INSERT INTO candidato (nombrecandidato, idoferta, idproveedor) VALUES ('"+candidato+"', "+idoferta+", "+idproveedor+")";
		
		try {
			ps = con.prepareStatement(consulta);
			valor = ps.executeUpdate(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		
		return valor;
	}
	
	public List<CandidatoVO> doGetCandidatosOferta(int idoferta){
		Connection con = BaseDatos.conexion();	
		ResultSet rs = null;
		CandidatoVO cvo = new CandidatoVO();
		List <CandidatoVO> candidatos = new ArrayList<CandidatoVO>();
		PreparedStatement ps = null;
		String consulta = "SELECT * FROM candidato WHERE idoferta="+idoferta;
		
		try{
			ps = con.prepareStatement(consulta);				
			rs = ps.executeQuery();
			
			while(rs.next()){
				cvo = new CandidatoVO();
				cvo.setIdcandidato(rs.getInt("idcandidato"));
			
				cvo.setNombrecandidato(rs.getString("nombrecandidato"));
				cvo.setIdoferta(idoferta);
				cvo.setIdproveedor(rs.getInt("idproveedor"));				
				candidatos.add(cvo);
			}
			
			rs.close();
		ps.close();
		con.close();
	} catch (SQLException e) {
	
		e.printStackTrace();
	} 
		return candidatos;		
	
	}
	
	public int doSelectedCandidato(int idOferta, int idCandidato){
		Connection con = BaseDatos.conexion();	
		
		int valor = 0;
		PreparedStatement ps = null;
		String consulta = "UPDATE candidato SET seleccionado='si' WHERE idoferta="+idOferta+" AND idCandidato="+idCandidato;
		String consulta2 = "UPDATE candidato SET seleccionado='no' WHERE idoferta="+idOferta+" AND idCandidato!="+idCandidato;
		try {
			ps = con.prepareStatement(consulta);
			valor = ps.executeUpdate(consulta);
			
			if(valor==1){
				ps = con.prepareStatement(consulta2);
				ps.executeUpdate(consulta2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
