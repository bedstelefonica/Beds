package beds.connectionbd;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDatos {
	public static Connection conexion(){
		Connection con = null;
		DataSource ds = null;
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:/DSMySQL");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
