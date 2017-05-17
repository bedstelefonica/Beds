package beds.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import beds.connectionbd.BaseDatos;
import beds.vo.ProveedorVO;

public class ProveedorBO {

	 public List<ProveedorVO> getAllProveedor() throws Exception{
		 ProveedorVO pvo;
		 
		 List <ProveedorVO> proveedores = new ArrayList<ProveedorVO>();
					
		Connection con = BaseDatos.conexion();		
		ResultSet rs = null;
		PreparedStatement ps = null;
		String consulta = "SELECT * FROM proveedor";
		 
		 try {
				ps = con.prepareStatement(consulta);				
				rs = ps.executeQuery();
				
				while(rs.next()){
					pvo = new ProveedorVO();
					pvo.setIdproveedor(rs.getInt("idproveedor"));
					pvo.setNombrepro(rs.getString("nombrepro"));
					pvo.setFechaingreso(rs.getString("fechaingreso"));
					pvo.setTipoproveedor(rs.getString("tipoproveedor"));
					proveedores.add(pvo);
				}
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		 
		return proveedores;
		 
	 }	

	 public List<String> doProveedorMenosOferta(int idoferta)
	 {
		 Connection con = BaseDatos.conexion();		
			ResultSet rs = null;
			PreparedStatement ps = null;
			List<String> po=new ArrayList<String>();
			String consulta="SELECT * FROM proveedor where proveedor.idproveedor NOT IN (SELECT  ofertaprovee.idproveedor FROM ofertaprovee WHERE idoferta="+idoferta+")";
			
			
			 try {
					ps = con.prepareStatement(consulta);				
					rs = ps.executeQuery();
					while(rs.next()){
						po.add(rs.getInt(1)+"-"+rs.getString(2));
					}
					rs.close();
					ps.close();
					con.close();
			 }catch(SQLException e){
				 e.printStackTrace();
			 }
			 return po;
	 }
	 public List<String> doProveedorOferta(int idoferta) throws Exception{
		 Connection con = BaseDatos.conexion();		
			ResultSet rs = null;
			PreparedStatement ps = null;
			List<String> po=new ArrayList<String>();
			
			String consulta = "SELECT idproveedor, nombrepro FROM proveedor where proveedor.idproveedor IN (SELECT  ofertaprovee.idproveedor FROM ofertaprovee WHERE idoferta="+idoferta+")";
			
			System.out.println("Consulta 1: "+consulta);
			 try {
					ps = con.prepareStatement(consulta);				
					rs = ps.executeQuery();
					while(rs.next()){
						System.out.println(rs.getInt(1)+"-"+rs.getString(2));
						po.add(rs.getInt(1)+"-"+rs.getString(2));
					}
					rs.close();
					ps.close();
					con.close();
			 }catch(SQLException e){
				 e.printStackTrace();
			 }
			 return po;
	 }
	 public ProveedorVO getProveedorById(int id) throws Exception{
		
		ProveedorVO pvo = new ProveedorVO();		
		Connection con = BaseDatos.conexion();		
		ResultSet rs = null;
		PreparedStatement ps = null;
		String consulta = "SELECT * FROM proveedor WHERE idproveedor="+id;
		 
		 try {
				ps = con.prepareStatement(consulta);				
				rs = ps.executeQuery();
				
				if(rs.next()){
					pvo = new ProveedorVO();
					pvo.setIdproveedor(rs.getInt("idproveedor"));
					pvo.setNombrepro(rs.getString("nombrepro"));
					pvo.setFechaingreso(rs.getString("fechaingreso"));
					pvo.setTipoproveedor(rs.getString("tipoproveedor"));
					
				}
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		 
		 return pvo;
		 
	 }	
	 
	 public int doCreateProveedor(ProveedorVO vo) throws Exception{
		return 0;
		 
	 }
	 
	 public void envioCorreoNoSelected(int idoferta, int idcandidato, int idproveedor) throws Exception{
		 Connection con = BaseDatos.conexion();		
			ResultSet rs = null;
			PreparedStatement ps = null;
						
			String consulta = "SELECT email FROM proveedor WHERE proveedor.idproveedor IN (SELECT  candidato.idproveedor FROM candidato WHERE idoferta="+idoferta+" AND idproveedor!="+idproveedor+ " AND idcandidato="+idcandidato+")";
			
			String mensaje = "Tu/s candidato/s no ha/n sido seleccionado/s para la oferta ";
			String subject = "Selección Candidato - Oferta "+idoferta;
			System.out.println("Consulta 1: "+consulta);
			 try {
					ps = con.prepareStatement(consulta);				
					rs = ps.executeQuery();
					while(rs.next()){
						envioCorreo(rs.getString("email"), mensaje, subject);
					}
					rs.close();
					ps.close();
					con.close();
			 }catch(SQLException e){
				 e.printStackTrace();
			 }
			 
	 }
	 
	 public void envioCorreo(String para, String msn, String subject){
		 //La direccion de envio (to)
		// String para = "melizaireth@gmail.com";
		 
		 //La direccion de la cuenta de envio (from)
		 String de = "beds.telefonica@gmail.com";
		 
		 //El servidor (host). En este caso usaremos localhost
		 //Usaremos la configuración de google gmail
		 
		 //Obtenemos la propiedad del sistema
		 Properties propiedades = System.getProperties();
		 
		 //Configuracion del servidor de correo
		 propiedades.put("mail.smtp.auth", "true");
		 propiedades.put("mail.smtp.starttls.enable", "true");
		 propiedades.put("mail.smtp.host", "smtp.gmail.com");		 
		 propiedades.put("mail.smtp.port","587");
		 propiedades.put("mail.smtp.mail.sender",para);		 
		 System.out.println("Cargo las propiedades");
		 //Obtenemos la sesión por defecto		 
		 Session sesion = Session.getInstance(propiedades,
				  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("beds.telefonica", "beds1234");
				}
			  });
		 System.out.println("Correo de BedsTelefonica");
		 try{
			 
			 //Creamos el objeto mensaje tipo MimeMenssage por defecto
			 MimeMessage mensaje = new MimeMessage(sesion);
			 
			 //Asignamos el "de/from" al header del correo
			 mensaje.setFrom(new InternetAddress(de));
				/*		 
			 for (String emailDestination : emailDestinations) {
				 mensaje.addRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestination));
			 }*/
			 
			 //Asignamos el "para/to" al header del correo
			 mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			 			 
			 mensaje.setSubject(subject);
			 
			 //Asignamos el asunto
			 mensaje.setText(msn);
			 
			 //Enviamos el correo
			 
			// Transport.send(mensaje);
			 Transport t = sesion.getTransport("smtp");
			 	t.connect((String)propiedades.get("mail.smtp.user"), "beds1234");
				t.sendMessage(mensaje, mensaje.getAllRecipients());
			//	t.close();
			 
			 System.out.println("Mensaje Enviado");
		 }catch(MessagingException e){
			 e.printStackTrace();
		 }
		 
	 }
	 
	 public String doGetEmailProveedor(int idproveedor){
		 Connection con = BaseDatos.conexion();		
		 ResultSet rs = null;
		 PreparedStatement ps = null;
		 String consulta = "SELECT email FROM proveedor WHERE idproveedor="+idproveedor;
		 String email = "";
		 try {
				ps = con.prepareStatement(consulta);				
				rs = ps.executeQuery();
				if(rs.next()){
					email = rs.getString("email");					
				}
				rs.close();
				ps.close();
				con.close();
		 }catch (SQLException e) {
				
				e.printStackTrace();
		 }
		 return email;
	 }
}

