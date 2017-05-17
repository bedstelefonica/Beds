package beds.mbeans.KPIs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beds.connectionbd.BaseDatos;

public class asdas {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = BaseDatos.conexion();
		ResultSet miResulset = null;	
		String consulta = "SELECT IMPORTE FROM factura WHERE PROVEEDOR = " + "Emrpesa1";
		PreparedStatement ps  = con.prepareStatement(consulta);
		miResulset = ps.executeQuery();
		while (miResulset.next()) {
			
			System.out.println(miResulset.getInt(5));

		}}
	}

