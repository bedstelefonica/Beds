package beds.vo;

import java.io.Serializable;

public class ProveedorVO implements Serializable{
	private static final long serialVersionUID =1;
		
	private int idproveedor;   
	private String nombrepro;
	private String fechaingreso;
	private String tipoproveedor;
	   
	public int getIdproveedor() {
		return idproveedor;
	}
	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}
	public String getNombrepro() {
		return nombrepro;
	}
	public void setNombrepro(String nombrepro) {
		this.nombrepro = nombrepro;
	}
	public String getFechaingreso() {
		return fechaingreso;
	}
	public void setFechaingreso(String fechaingreso) {
		this.fechaingreso = fechaingreso;
	}
	public String getTipoproveedor() {
		return tipoproveedor;
	}
	public void setTipoproveedor(String tipoproveedor) {
		this.tipoproveedor = tipoproveedor;
	}
	   
	   public String get(int i){
		   
		   switch(i){
		   case 2: return nombrepro;
		   default: return nombrepro;
		   }
	   }
   
}
