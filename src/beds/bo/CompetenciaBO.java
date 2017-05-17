package beds.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beds.connectionbd.BaseDatos;
import beds.vo.CompetenciaVO;

public class CompetenciaBO {

	public List<String> getAllTipo() throws Exception{
		Connection con = BaseDatos.conexion();	
		ResultSet rs = null;
		List<String> roles = new ArrayList<String>();
		String consulta = "SELECT DISTINCT tipo FROM competencia";
		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement(consulta);				
rs = ps.executeQuery();
			
			while(rs.next()){				
				roles.add(rs.getString("tipo"));
			}
			rs.close();
		ps.close();
		con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	 
			return roles;
}
	
	
	public List<String> getCompetenciasPersonales(){
		Connection con = BaseDatos.conexion();	
		ResultSet rs = null;
		List<String> compersonales = new ArrayList<String>();
		String consulta = "SELECT * FROM competencia WHERE tipo='personales'";
		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement(consulta);				
			rs = ps.executeQuery();
			
			while(rs.next()){				
				compersonales.add(rs.getInt("idcompetencia")+"-"+rs.getString("descripcion"));
			}
			rs.close();
		ps.close();
		con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	 
			return compersonales;
	}
	

	public List<String> getCompetenciasPersonalesByIdOferta(int idOferta, char dual){
		Connection con = BaseDatos.conexion();	
		ResultSet rs = null;
		List<String> compersonales = new ArrayList<String>();
		String consulta = "";
		if(dual=='t'){
			//System.out.println("Aqui");
				consulta = "SELECT * FROM competencia where tipo='personales' and competencia.idcompetencia IN (SELECT  ofertacompetencia.idcompetencia FROM ofertacompetencia WHERE idoferta="+idOferta+")";	
		}else{
				consulta = "SELECT * FROM competencia where tipo='personales' and competencia.idcompetencia NOT IN (SELECT  ofertacompetencia.idcompetencia FROM ofertacompetencia WHERE idoferta="+idOferta+")";	
		}
				
		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement(consulta);				
			rs = ps.executeQuery();
			
			while(rs.next()){				
			//	System.out.println(rs.getInt("idcompetencia")+"-"+rs.getString("descripcion"));
				compersonales.add(rs.getInt("idcompetencia")+"-"+rs.getString("descripcion"));
			}
			rs.close();
		ps.close();
		con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	 
			return compersonales;
	}
	
	public List<CompetenciaVO> getCompetenciaByTipo(String tipo) throws Exception{
		
			Connection con = BaseDatos.conexion();	
			ResultSet rs = null;
			List<CompetenciaVO> competecnica = new ArrayList<CompetenciaVO>();
			
			String consulta = "SELECT * FROM competencia WHERE tipo='"+tipo+"'";
			PreparedStatement ps = null;
			try{
				ps = con.prepareStatement(consulta);				
				rs = ps.executeQuery();
				CompetenciaVO competencia = new CompetenciaVO();
				while(rs.next()){		
					competencia = new CompetenciaVO();
					competencia.setIdcompetencia(rs.getInt("idcompetencia"));;
					competencia.setDescripcion(rs.getString("descripcion"));
					competencia.setTipo(rs.getString("tipo"));
					competecnica.add(competencia);
				}
				System.out.println("Tamaño de la lista: "+competecnica.size());
				rs.close();
			ps.close();
			con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		 
				return competecnica;
		
	}
}