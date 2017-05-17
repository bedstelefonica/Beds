package beds.vo;

import java.io.Serializable;

public class UsuarioVO implements Serializable{
	private static final long serialVersionUID =1;
	
	private int idusuario;
	private String user;
	private String password;
	private String perfil;
	private String nombre;
	private String apellido;
	private int idrelacion;
	
	
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
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
	public int getIdrelacion() {
		return idrelacion;
	}
	public void setIdrelacion(int idrelacion) {
		this.idrelacion = idrelacion;
	}
	
			

}
