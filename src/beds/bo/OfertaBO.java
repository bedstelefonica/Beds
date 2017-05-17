package beds.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beds.connectionbd.BaseDatos;
import beds.dao.OfertaDAO;
import beds.vo.CompetenciaVO;
import beds.vo.OfertaVO;

public class OfertaBO {

	public String doGetEstado(int idoferta) {
		Connection con = BaseDatos.conexion();
		ResultSet rs = null;
		String estado = "";
		PreparedStatement ps = null;
		String consulta = "SELECT estado FROM oferta WHERE idoferta=" + idoferta;
		try {

			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			if (rs.next()) {
				estado = rs.getString(1);				
			}

			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return estado;
	}

	public int doCreateOferta(String puesto, String perfil, String fecha) throws Exception {
		Connection con = BaseDatos.conexion();
		ResultSet rs = null;
		int valor = 0;
		PreparedStatement ps = null;
		String consulta = "INSERT INTO oferta (puesto, perfil, estado, fechacrea) VALUES ('" + puesto + "', '" + perfil
				+ "', 'abierta', '" + fecha + "')";
		System.out.println(consulta);
		try {
			ps = con.prepareStatement(consulta);
			valor = ps.executeUpdate(consulta);

			if (valor == 1) {
				consulta = "SELECT MAX(idoferta) AS idoferta FROM oferta";
				ps = con.prepareStatement(consulta);
				rs = ps.executeQuery();
				if (rs.next()) {
					valor = rs.getInt(1);
					System.out.println(valor);
				}

			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return valor;

	}

	public int doCreateOferta(int idoferta, int idproveedor) throws Exception {
		Connection con = BaseDatos.conexion();
		int valor = 0;
		PreparedStatement ps = null;
		String consulta = "INSERT INTO ofertaprovee (idoferta, idproveedor) VALUES ('" + idoferta + "', '" + idproveedor
				+ "')";
		try {
			ps = con.prepareStatement(consulta);
			valor = ps.executeUpdate(consulta);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return valor;
	}

	public int doCreateOfertaCompetencia(int idoferta, int idcompetencia, String segundo, String tercer) {
		Connection con = BaseDatos.conexion();
		int valor = 0;
		PreparedStatement ps = null;
		String consulta = "INSERT INTO ofertacompetencia (idoferta, idcompetencia, segundonivel, tercernivel) VALUES ('"
				+ idoferta + "', '" + idcompetencia + "', '" + segundo + "', '" + tercer + "')";
		try {
			ps = con.prepareStatement(consulta);
			valor = ps.executeUpdate(consulta);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return valor;
	}

	public List<OfertaVO> doSelectOfertaProveedor(int idproveedor) {
		// Este metodo recibira por parametro el idproveedor
		OfertaVO ovo;
		List<OfertaVO> ofertas = new ArrayList<OfertaVO>();
		Connection con = BaseDatos.conexion();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String consulta = "SELECT * FROM oferta WHERE oferta.idoferta IN (SELECT ofertaprovee.idoferta FROM ofertaprovee WHERE idproveedor =2)";
		try {
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();

			while (rs.next()) {
				ovo = new OfertaVO();
				ovo.setIdoferta(rs.getInt("idoferta"));
				ovo.setPuesto(rs.getString("puesto"));
				ovo.setPerfil(rs.getString("perfil"));
				ovo.setEstado(rs.getString("estado"));
				ofertas.add(ovo);
			}

			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return ofertas;

	}

	public List<OfertaVO> doSelectOfertas() throws Exception {
		OfertaVO ovo;
		List<OfertaVO> ofertas = new ArrayList<OfertaVO>();
		Connection con = BaseDatos.conexion();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String consulta = "SELECT * FROM oferta";
		try {
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();

			while (rs.next()) {
				ovo = new OfertaVO();
				ovo.setIdoferta(rs.getInt("idoferta"));
				ovo.setPuesto(rs.getString("puesto"));
				ovo.setPerfil(rs.getString("perfil"));
				ovo.setEstado(rs.getString("estado"));
				ofertas.add(ovo);
			}

			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return ofertas;

	}

	public List<CompetenciaVO> getCompetenciaByIdOferta(int idOferta, String tipo) {
		CompetenciaVO cvo;
		List<CompetenciaVO> competencias = new ArrayList<CompetenciaVO>();
		Connection con = BaseDatos.conexion();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String consulta = "SELECT * FROM competencia where competencia.idcompetencia IN (SELECT  ofertacompetencia.idcompetencia FROM ofertacompetencia WHERE idoferta="
				+ idOferta + " and tipo='" + tipo + "')";
		// System.out.println(consulta);
		try {
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();

			while (rs.next()) {
				// System.out.println("Entra en este ciclo");
				cvo = new CompetenciaVO();
				// System.out.println(rs.getInt("idcompetencia")+"_"+rs.getString("descripcion"));
				cvo.setIdcompetencia(rs.getInt("idcompetencia"));
				cvo.setDescripcion(rs.getString("descripcion"));
				cvo.setTipo(rs.getString("tipo"));

				competencias.add(cvo);
			}

			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return competencias;
	}

	public int actualizarOfertaProveedor(int idproveedor, int idoferta) {
		Connection con = BaseDatos.conexion();
		ResultSet rs = null;
		PreparedStatement ps = null;
		int valor = 0;

		String consulta = "SELECT * FROM ofertaprovee WHERE idoferta=" + idoferta + " AND idproveedor=" + idproveedor;
		String consulta2 = "INSERT INTO ofertaprovee (idproveedor, idoferta) VALUES (" + idproveedor + ", " + idoferta
				+ ")";
		try {
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();

			if (!rs.next()) {
				ps = con.prepareStatement(consulta2);
				valor = ps.executeUpdate(consulta2);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valor;
	}

	public int actualizarOfertaComPersonal(int idcompetencia, int idoferta) {
		Connection con = BaseDatos.conexion();
		ResultSet rs = null;
		PreparedStatement ps = null;
		int valor = 0;

		String consulta = "SELECT * FROM ofertacompetencia WHERE idoferta=" + idoferta + " AND idcompetencia="
				+ idcompetencia;
		String consulta2 = "INSERT INTO ofertacompetencia (idoferta, idcompetencia) VALUES (" + idoferta + ", "
				+ idcompetencia + ")";

		try {
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();

			if (!rs.next()) {
				ps = con.prepareStatement(consulta2);
				valor = ps.executeUpdate(consulta2);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valor;
	}

	public void cambiarEstado(int idoferta, String estado) {
		Connection con = BaseDatos.conexion();

		PreparedStatement ps = null;
		String consulta = "UPDATE oferta SET estado='" + estado + "' WHERE idoferta=" + idoferta;
		try {
			ps = con.prepareStatement(consulta);
			ps.executeUpdate(consulta);

			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
