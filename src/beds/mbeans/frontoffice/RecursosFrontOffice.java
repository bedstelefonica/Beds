package beds.mbeans.frontoffice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import beds.bo.CandidatoBO;
import beds.bo.OfertaBO;
import beds.bo.SessionUtils;
import beds.bo.UsuarioBO;
import beds.dao.OfertaDAO;
import beds.vo.CandidatoVO;
import beds.vo.CompetenciaVO;
import beds.vo.OfertaVO;

@ManagedBean(name="frontoferta")
@SessionScoped
public class RecursosFrontOffice implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "")
	private String nombreCandidato;
	
	@ManagedProperty(value = "")
	private List<CandidatoVO> candidatos;
	
	@ManagedProperty(value = "")
	private int idproveedor;
	@ManagedProperty(value = "")
	private int idoferta;
	@ManagedProperty(value = "")
	private String estado;
	
	@ManagedProperty(value = "")
	private List<OfertaVO> ofertas;
	@ManagedProperty(value = "")
	private List<CompetenciaVO> compTencicas;
	@ManagedProperty(value = "")
	private List<CompetenciaVO> compPersonales;
	
	@ManagedProperty(value = "")
	private String mensaje;
		
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	private UploadedFile file;
	 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
	public int getIdoferta() {
		return idoferta;
	}


	public List<CandidatoVO> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<CandidatoVO> candidatos) {
		this.candidatos = candidatos;
	}

	public void setIdoferta(int idoferta) {
		this.idoferta = idoferta;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public List<CompetenciaVO> getCompPersonales() {
		return compPersonales;
	}


	public void setCompPersonales(List<CompetenciaVO> compPersonales) {
		this.compPersonales = compPersonales;
	}

	public List<CompetenciaVO> getCompTencicas() {
		return compTencicas;
	}
	

	public void setCompTencicas(List<CompetenciaVO> compTencicas) {
		this.compTencicas = compTencicas;
	}

	@ManagedProperty(value = "")
	private OfertaVO oferta = new OfertaVO();
	
	public int getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}

	public String getNombreCandidato() {
		return nombreCandidato;
	}

	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}

	public List<OfertaVO> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertaVO> ofertas) {
		this.ofertas = ofertas;
	}

	public OfertaVO getOferta() {
		return oferta;
	}

	public void setOferta(OfertaVO oferta) {
		this.oferta = oferta;
	}
	int identificadoro =0;
	@PostConstruct
	public void mostrarOferta() throws Exception{
		OfertaBO obo = new OfertaBO();	
		UsuarioBO ubo = new UsuarioBO();
		HttpSession session = SessionUtils.getSession();
		String usuario = (String) session.getAttribute("username");
		//String profile = (String) session.getAttribute("profile");
		idproveedor = ubo.getIdProveedorByUser(usuario);
		ofertas = obo.doSelectOfertaProveedor(idproveedor);
		
		
	}
	
	public void oyenteAjax(AjaxBehaviorEvent evento){
		 OfertaBO obo = new OfertaBO();
		 CandidatoBO cbo = new CandidatoBO();
		 oferta = (OfertaVO) evento.getComponent().getAttributes().get("ofertaFront");
		 candidatos = 	cbo.doGetCandidatosByProvOferta(oferta.getIdoferta(), idproveedor);
		 compTencicas = obo.getCompetenciaByIdOferta(oferta.getIdoferta(), oferta.getPuesto());
		 compPersonales = obo.getCompetenciaByIdOferta(oferta.getIdoferta(), "personales");		      
	}
	
	
	public void oyente(AjaxBehaviorEvent evento){
		
		CandidatoBO cbo = new CandidatoBO();	
		OfertaBO obo = new OfertaBO();
		
	    /*FacesContext context = FacesContext.getCurrentInstance();
	    String s = context.getExternalContext().getRequestParameterMap().get("idof");  */
	    oferta = (OfertaVO) evento.getComponent().getAttributes().get("idof");
	    //idoferta= Integer.parseInt(s);	
	    
	    //String estado = obo.doGetEstado(idoferta);
	    
	    if(oferta.getEstado().equals(OfertaDAO.ESTADO_OFERTA_ABIERTO)){
	    	
	        cbo.doSetCandidatoProveedor(nombreCandidato, oferta.getIdoferta(), idproveedor);
	    	mensaje="El candidato "+nombreCandidato+" fue dado de alta en la oferta";
	    }else{
	    	
	    	mensaje="No se puede dar de alta al candidato con la oferta en estado ";
	    }		
	   
	}
	
	public String mensajeCandidato(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		String msg2 = "";
		if(oferta.getEstado().equals(OfertaDAO.ESTADO_OFERTA_ABIERTO)){
			msg2 = "El Candidato fue dado de alta";
		}else{
			msg2="No se puede dar de alta al candidato en la oferta ya que esta se encuentra en estado "+oferta.getEstado();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg2);
		context.addMessage(null, msg);
		
		return "Hola";
	}
	
	
	public void upload(FileUploadEvent event) {
		
		
		file = event.getFile();
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        //FacesContext.getCurrentInstance().addMessage(null, message);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String ruta = ec.getRealPath("C:/Users/Melissa/Desktop/pruebas/" + file.getFileName());
        try {
        	FileInputStream in = (FileInputStream) file.getInputstream();
        	FileOutputStream out = new FileOutputStream(ruta);
        	byte [] buffer = new byte [(int)file.getSize()];
        	int contador = 0;
        	while((contador = in.read(buffer))!=-1){
        		
        		out.write(buffer, 0, contador);
        	}
			in.close();
			out.close();
        	//file.write();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	
}
