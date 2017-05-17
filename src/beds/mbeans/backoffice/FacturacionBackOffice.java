package beds.mbeans.backoffice;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.sql.DataSource;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import beds.vo.FacturaVO;

import beds.connectionbd.BaseDatos;

@ManagedBean(name = "FacturacionBackOffice")
@SessionScoped

public class FacturacionBackOffice implements Serializable {

	private static final long serialVersionUID = 1L;

	ArrayList<FacturaVO> ListaFactura;
	ArrayList<FacturaVO> FiltroEstado;
	FacturaVO ObjetoDatosFactura = new FacturaVO();
	UploadedFile file;
	

	@PostConstruct
	public void rellenar() {

		Connection con = BaseDatos.conexion();
		ResultSet miResulset = null;
		PreparedStatement ps = null;
		String consulta = "SELECT * FROM factura";

		ListaFactura = new ArrayList<FacturaVO>();
		FiltroEstado = new ArrayList<FacturaVO>();

		try {
			ps = con.prepareStatement(consulta);
			miResulset = ps.executeQuery();

			while (miResulset.next()) {
				ObjetoDatosFactura = new FacturaVO();
				ObjetoDatosFactura.setIdfactura(miResulset.getInt(1));
				ObjetoDatosFactura.setProveedor(miResulset.getString(2));
				ObjetoDatosFactura.setEstado(miResulset.getString(3));
				ObjetoDatosFactura.setConcepto(miResulset.getString(4));
				ObjetoDatosFactura.setImporte(miResulset.getInt(5));
				ObjetoDatosFactura.setNrofactura(miResulset.getString(6));
				ListaFactura.add(ObjetoDatosFactura);
			}
			ps.close();
			con.close();
			ObjetoDatosFactura = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void oyente(AjaxBehaviorEvent evento) {

		ObjetoDatosFactura = (FacturaVO) evento.getComponent().getAttributes().get("DFactura");
		

	}

	public void mensajeModificacion() {

		FacesMessage msg = new FacesMessage("El Usuario a sido modificado con exito");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void modifica(AjaxBehaviorEvent evento) {

	
		Connection con = BaseDatos.conexion();
		String consulta = "update factura set proveedor = ?, estado =?, concepto = ?, importe = ?, nrofactura = ? where idfactura =? ";

		PreparedStatement preparada;
	

		try {

			preparada = con.prepareStatement(consulta);
			preparada.setString(1, ObjetoDatosFactura.getProveedor());
			preparada.setString(2, ObjetoDatosFactura.getEstado());
			preparada.setString(3, ObjetoDatosFactura.getConcepto());
			preparada.setInt(4, ObjetoDatosFactura.getImporte());
			preparada.setString(5, ObjetoDatosFactura.getNrofactura());
			preparada.setInt(6, ObjetoDatosFactura.getIdfactura());
			preparada.executeUpdate();



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
			file.write("C:/Users/dani/Desktop/Pruebas/" + file.getFileName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<FacturaVO> getListaFactura() {
		return ListaFactura;
	}

	public void setListaFactura(ArrayList<FacturaVO> listaFactura) {
		ListaFactura = listaFactura;
	}

	public ArrayList<FacturaVO> getFiltroEstado() {
		return FiltroEstado;
	}

	public void setFiltroEstado(ArrayList<FacturaVO> filtroEstado) {
		FiltroEstado = filtroEstado;
	}

	public FacturaVO getObjetoDatosFactura() {
		return ObjetoDatosFactura;
	}

	public void setObjetoDatosFactura(FacturaVO objetoDatosFactura) {
		ObjetoDatosFactura = objetoDatosFactura;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}


}
