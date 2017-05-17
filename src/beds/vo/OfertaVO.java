package beds.vo;

import java.io.Serializable;

public class OfertaVO implements Serializable{
	private static final long serialVersionUID =1;
	
	private int idoferta;
	private String puesto;
	private String perfil;
	private String estado;
	 
	public int getIdoferta() {
		return idoferta;
	}
	public void setIdoferta(int idoferta) {
		this.idoferta = idoferta;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	 
	 
}
