package beds.vo;

import java.io.Serializable;

public class CompetenciaVO implements Serializable{
	private static final long serialVersionUID =1;
	
	private int idcompetencia;
	private String descripcion;
	private String tipo;
	
	public int getIdcompetencia() {
		return idcompetencia;
	}
	public void setIdcompetencia(int idcompetencia) {
		this.idcompetencia = idcompetencia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
