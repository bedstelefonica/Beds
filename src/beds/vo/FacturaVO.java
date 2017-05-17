package beds.vo;

import java.io.Serializable;

public class FacturaVO implements Serializable{
	private static final long serialVersionUID =1;
	 
	private int idfactura;
	private String proveedor;
	private String estado;
	private String concepto;
	private int importe;
	private String nrofactura;
	private String fecha;
	
	
	public int getIdfactura() {
		return idfactura;
	}
	public void setIdfactura(int idfactura) {
		this.idfactura = idfactura;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public int getImporte() {
		return importe;
	}
	public void setImporte(int importe) {
		this.importe = importe;
	}
	public String getNrofactura() {
		return nrofactura;
	}
	public void setNrofactura(String nrofactura) {
		this.nrofactura = nrofactura;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
