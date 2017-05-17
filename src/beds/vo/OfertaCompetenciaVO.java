package beds.vo;

import java.io.Serializable;

public class OfertaCompetenciaVO implements Serializable{
	private static final long serialVersionUID =1;
	
	private int idoferta;
	private int idcompetencia;
	private String segundoNivel;
	private String tercerNivel;
	private String experiencia;
	private String requerido;
	
	public int getIdoferta() {
		return idoferta;
	}
	public void setIdoferta(int idoferta) {
		this.idoferta = idoferta;
	}
	public int getIdcompetencia() {
		return idcompetencia;
	}
	public void setIdcompetencia(int idcompetencia) {
		this.idcompetencia = idcompetencia;
	}
	public String getSegundoNivel() {
		return segundoNivel;
	}
	public void setSegundoNivel(String segundoNivel) {
		this.segundoNivel = segundoNivel;
	}
	public String getTercerNivel() {
		return tercerNivel;
	}
	public void setTercerNivel(String tercerNivel) {
		this.tercerNivel = tercerNivel;
	}
	public String getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}
	public String getRequerido() {
		return requerido;
	}
	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}
	
	
}
