package beds.mbeans.backoffice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import beds.connectionbd.BaseDatos;
import beds.vo.FacturaVO;
import beds.vo.UsuarioVO;

@ManagedBean(name = "AltaUsuario")
@SessionScoped
public class AltaUsuarios {

	UsuarioVO Usuario = new UsuarioVO();
	ArrayList<UsuarioVO> ListaUsuarios;
	ArrayList<FacturaVO> FiltroEstado;
	UsuarioVO ObjetoDatosUsuario = new UsuarioVO();
	UploadedFile file;
	Integer idusuario;
	String user;
	String password;
	String perfil;
	String nombre;
	String apellido;
	Integer idrelacion;

	@PostConstruct
	public void rellenarTablaUsuarios() {

		Connection con = BaseDatos.conexion();
		ResultSet miResulset = null;
		PreparedStatement ps = null;
		String consulta = "SELECT * FROM usuario";

		ListaUsuarios = new ArrayList<UsuarioVO>();
		FiltroEstado = new ArrayList<FacturaVO>();

		try {
			ps = con.prepareStatement(consulta);
			miResulset = ps.executeQuery();

			while (miResulset.next()) {
				ObjetoDatosUsuario = new UsuarioVO();
				ObjetoDatosUsuario.setIdusuario(miResulset.getInt(1));
				ObjetoDatosUsuario.setUser(miResulset.getString(2));
				ObjetoDatosUsuario.setPassword(miResulset.getString(3));
				ObjetoDatosUsuario.setPerfil(miResulset.getString(4));
				ObjetoDatosUsuario.setNombre(miResulset.getString(5));
				ObjetoDatosUsuario.setApellido(miResulset.getString(6));
				ObjetoDatosUsuario.setIdrelacion(miResulset.getInt(7));
				ListaUsuarios.add(ObjetoDatosUsuario);

			}

			ObjetoDatosUsuario = null;

			miResulset.close();
			ps.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void oyente(AjaxBehaviorEvent evento) {

		ObjetoDatosUsuario = (UsuarioVO) evento.getComponent().getAttributes().get("DUsuario");
		System.out.println(ObjetoDatosUsuario.getNombre());

	}

	public void addUsuarios() {

		FacesMessage message = new FacesMessage("Exito. Usuarios nuevo creado");
		FacesContext.getCurrentInstance().addMessage(null, message);
		Connection con = BaseDatos.conexion();

		PreparedStatement ps = null;

		String consulta = "INSERT INTO USUARIO (IDUSUARIO, USER, PASSWORD, PERFIL) VALUES (?,?,?,?)";

		try {
			ps = con.prepareStatement(consulta);

			ps.setInt(1, idusuario);
			ps.setString(2, user);
			ps.setString(3, password);
			ps.setString(4, perfil);
			ps.execute();

			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void mensajemodificar() {

		FacesMessage message = new FacesMessage("Usuario modificado con Exito");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void modifica(AjaxBehaviorEvent evento) {

		Connection con = BaseDatos.conexion();
		String consulta = "update factura set user = ?, password =?, perfil = ?, nombre = ?, apellido = ?, idrelacion = ?  where idfactura ="
				+ ObjetoDatosUsuario.getIdusuario() + "";

		PreparedStatement preparada;
		;

		try {

			preparada = con.prepareStatement(consulta);
			preparada.setString(1, ObjetoDatosUsuario.getUser());
			preparada.setString(2, ObjetoDatosUsuario.getPassword());
			preparada.setString(3, ObjetoDatosUsuario.getPerfil());
			preparada.setString(4, ObjetoDatosUsuario.getNombre());
			preparada.setString(5, ObjetoDatosUsuario.getApellido());
			preparada.setInt(6, ObjetoDatosUsuario.getIdrelacion());

			preparada.executeUpdate();
			preparada.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}

	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		FacesMessage message = new FacesMessage("Exito, el archivo ", event.getFile().getFileName() + " esta cargado");
		FacesContext.getCurrentInstance().addMessage(null, message);
		try {
			file.write("C:/Users/vayderk/Desktop/Pruebas/" + file.getFileName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UsuarioVO getUsuario() {
		return Usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		Usuario = usuario;
	}

	public ArrayList<UsuarioVO> getListaUsuarios() {
		return ListaUsuarios;
	}

	public void setListaUsuarios(ArrayList<UsuarioVO> listaUsuarios) {
		ListaUsuarios = listaUsuarios;
	}

	public ArrayList<FacturaVO> getFiltroEstado() {
		return FiltroEstado;
	}

	public void setFiltroEstado(ArrayList<FacturaVO> filtroEstado) {
		FiltroEstado = filtroEstado;
	}

	public UsuarioVO getObjetoDatosUsuario() {
		return ObjetoDatosUsuario;
	}

	public void setObjetoDatosUsuario(UsuarioVO objetoDatosUsuario) {
		ObjetoDatosUsuario = objetoDatosUsuario;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getIdrelacion() {
		return idrelacion;
	}

	public void setIdrelacion(Integer idrelacion) {
		this.idrelacion = idrelacion;
	}

}
