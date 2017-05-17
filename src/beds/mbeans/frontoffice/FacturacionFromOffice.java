package beds.mbeans.frontoffice;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.sql.DataSource;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import beds.vo.FacturaVO;

import beds.connectionbd.BaseDatos;

@ManagedBean(name = "FacturacionFromOffice")
@SessionScoped

public class FacturacionFromOffice implements Serializable {

	private static final long serialVersionUID = 1L;

	ArrayList<FacturaVO> ListaFacturas;
	FacturaVO Factura = new FacturaVO();
	Connection con = null;
	DataSource ds = null;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	Integer idfactura;
	String proveedor;
	String estado;
	String concepto;
	Integer importe;
	String nrofactura;
	String fecha;

	@PostConstruct
	public void rellenar() {

		Connection con = BaseDatos.conexion();
		ResultSet miResulset = null;
		PreparedStatement ps = null;

		ListaFacturas = new ArrayList<FacturaVO>();
		try {
			String consulta = "SELECT * FROM FACTURA";

			ps = con.prepareStatement(consulta);

			miResulset = ps.executeQuery();

			while (miResulset.next()) {
				Factura = new FacturaVO();

				Factura.setIdfactura(miResulset.getInt(1));
				Factura.setProveedor(miResulset.getString(2));
				Factura.setEstado(miResulset.getString(3));
				Factura.setConcepto(miResulset.getString(4));
				Factura.setImporte(miResulset.getInt(5));
				Factura.setNrofactura(miResulset.getString(6));
				Factura.setFecha(miResulset.getString(7));
				ListaFacturas.add(Factura);
				Factura = null;
			}
			miResulset.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void borrarFactura(AjaxBehaviorEvent evento) {
		FacesMessage message = new FacesMessage("Factura creada correctamente");
		FacesContext.getCurrentInstance().addMessage(null, message);		

		Factura = (FacturaVO) evento.getComponent().getAttributes().get("DFactura");
		

		Connection con = BaseDatos.conexion();
		PreparedStatement ps = null;

		try {
			String consulta = "DELETE FROM FACTURA WHERE idfactura = ?";

			ps = con.prepareStatement(consulta);
			ps.setInt(1, Factura.getIdfactura());

			ps.executeUpdate();

			ps.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void mensaje() {

		FacesMessage message = new FacesMessage("Factura creada correctamente");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}
	
	public void mensajeborrar() {

		FacesMessage message = new FacesMessage("Factura borrada con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void addFactura() {

	
		Connection con = BaseDatos.conexion();

		PreparedStatement ps = null;

		String consulta = "INSERT INTO FACTURA (IDFACTURA, PROVEEDOR, ESTADO, CONCEPTO, IMPORTE, NROFACTURA, FECHA) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ps = con.prepareStatement(consulta);

			ps.setInt(1, idfactura);
			ps.setString(2, proveedor);
			ps.setString(3, estado);
			ps.setString(4, concepto);
			ps.setInt(5, importe);
			ps.setString(6, nrofactura);
			ps.setString(7, dateFormat.format(date));
	
			
			ps.execute();

			ps.close();
			con.close();
		} catch (SQLException e) {

			
		}
		
	}

	public void subirArchivo(FileUploadEvent event) {

		UploadedFile file = event.getFile();
		FacesMessage message = new FacesMessage("Exito", event.getFile().getFileName() + " Subido Correctamente.");
		FacesContext.getCurrentInstance().addMessage(null, message);

		try {
			file.write("C:/Users/dani/Desktop/Pruebas/" + file.getFileName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<FacturaVO> getListaFacturas() {
		return ListaFacturas;
	}

	public void setListaFacturas(ArrayList<FacturaVO> listaFacturas) {
		ListaFacturas = listaFacturas;
	}

	public FacturaVO getFactura() {
		return Factura;
	}

	public void setFactura(FacturaVO factura) {
		Factura = factura;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(Integer idfactura) {
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

	public Integer getImporte() {
		return importe;
	}

	public void setImporte(Integer importe) {
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
