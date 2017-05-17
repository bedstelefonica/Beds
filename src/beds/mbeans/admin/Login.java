package beds.mbeans.admin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import beds.bo.SessionUtils;
import beds.bo.UsuarioBO;
import beds.dao.UsuarioDAO;


import beds.dao.OfertaDAO;

@ManagedBean(name = "log")
@SessionScoped // Ambito de vida
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "")
	String username;
	@ManagedProperty(value = "")
	String password;

	@ManagedProperty(value = "")
	String perfil;

	@ManagedProperty(value = "")
	private Map<String, String> perfiles;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public void setPerfiles(Map<String, String> perfiles) {
		this.perfiles = perfiles;
	}

	public Map<String, String> getPerfiles() {
		return perfiles;
	}

	@PostConstruct
	public void getProfiles() {

		perfiles = new HashMap<String, String>();
		perfiles.put("Back Oficce", UsuarioDAO.PERFIL_BACKOFFICE);
		perfiles.put("Front Oficce", UsuarioDAO.PERFIL_FRONTOFFICE);
	}

	public String actionValidar(){
		System.out.println("Usuario: "+username);
		System.out.println("Password: "+password);
		UsuarioBO ubo = new UsuarioBO();
		
		perfil = ubo.getUserByUserId(username, password);				
		
		
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("username", username);
		session.setAttribute("profile", perfil);
		if(perfil.equals(UsuarioDAO.PERFIL_BACKOFFICE)){
			return "backoffice";
		}else{
			if(perfil.equals(UsuarioDAO.PERFIL_FRONTOFFICE)){
				return "frontoffice";
			}else{
				 return "fail";
			}
		}				
    }
}
