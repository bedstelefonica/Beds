package beds.mbeans.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import beds.vo.OfertaVO;
import beds.vo.UsuarioVO;

@ManagedBean(name="adminuser")
@SessionScoped //Ambito de vida
public class AdministrarUsuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "")
	private String user;
	@ManagedProperty(value = "")
	private String pasword;
	@ManagedProperty(value = "")
	private String nombre;
	@ManagedProperty(value = "")
	private String apellido;
	@ManagedProperty(value = "")
	private String perfil;
	
	@ManagedProperty(value = "")
	private UsuarioVO usuario;
	
	@PostConstruct
	public void manejoUsuario(){
		
		
		
	}
	
	

}
