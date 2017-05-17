package beds.mbeans.backoffice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DualListModel;

import beds.bo.CandidatoBO;
import beds.bo.CompetenciaBO;
import beds.bo.OfertaBO;
import beds.bo.ProveedorBO;
import beds.bo.SessionUtils;
import beds.dao.OfertaDAO;
import beds.vo.CandidatoVO;
import beds.vo.CompetenciaVO;
import beds.vo.OfertaCompetenciaVO;
import beds.vo.OfertaVO;
import beds.vo.ProveedorVO;

@ManagedBean(name = "actualizabo")
@SessionScoped // Ambito de vida
public class ActualizaOferta implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	@ManagedProperty(value = "")
	private List<OfertaVO> ofertas;

	@ManagedProperty(value = "")
	private OfertaVO oferta;// = new OfertaVO();

	@ManagedProperty(value = "")
	private DualListModel<String> proveedores;

	@ManagedProperty(value = "")
	private List<ProveedorVO> proveedoresSource;

	@ManagedProperty(value = "")
	private List<ProveedorVO> proveedoresTarg;

	@ManagedProperty(value = "")
	private DualListModel<String> comperBack;

	@ManagedProperty(value = "")
	private List<String> compersonalesSource;

	@ManagedProperty(value = "")
	private List<String> compersonalesTarg;

	@ManagedProperty(value = "")
	List<String> nompro;

	@ManagedProperty(value = "")
	List<String> nomproTarget;

	@ManagedProperty(value = "")
	private Map<String, String> estado;
	
	@ManagedProperty(value = "")
	private List<CompetenciaVO> compTencicas;
	@ManagedProperty(value = "")
	private List<CandidatoVO> candidatos;
	
	private CandidatoVO selectCandidato;
	
	
	public CandidatoVO getSelectCandidato() {
		return selectCandidato;
	}

	public void setSelectCandidato(CandidatoVO selectCandidato) {
		this.selectCandidato = selectCandidato;
	}

	private List<OfertaCompetenciaVO> ofertaCompetencia;
	
	private boolean disable;
	
	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public List<CandidatoVO> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<CandidatoVO> candidatos) {
		this.candidatos = candidatos;
	}

	public Map<String, String> getEstado() {
		return estado;
	}

	public void setEstado(Map<String, String> estado) {
		this.estado = estado;
	}

	public List<OfertaCompetenciaVO> getOfertaCompetencia() {
		return ofertaCompetencia;
	}

	public void setOfertaCompetencia(List<OfertaCompetenciaVO> ofertaCompetencia) {
		this.ofertaCompetencia = ofertaCompetencia;
	}

	public List<CompetenciaVO> getCompTencicas() {
		return compTencicas;
	}

	public void setCompTencicas(List<CompetenciaVO> compTencicas) {
		this.compTencicas = compTencicas;
	}

	public DualListModel<String> getComperBack() {
		return comperBack;
	}

	public void setComperBack(DualListModel<String> comperBack) {
		this.comperBack = comperBack;
	}

	public List<String> getCompersonalesSource() {
		return compersonalesSource;
	}

	public void setCompersonalesSource(List<String> compersonalesSource) {
		this.compersonalesSource = compersonalesSource;
	}

	public List<String> getCompersonalesTarg() {
		return compersonalesTarg;
	}

	public void setCompersonalesTarg(List<String> compersonalesTarg) {
		this.compersonalesTarg = compersonalesTarg;
	}

	public Map<String, String> getPerfiles() {
		return estado;
	}

	public void setPerfiles(Map<String, String> estado) {
		this.estado = estado;
	}

	public List<String> getNompro() {
		return nompro;
	}

	public void setNompro(List<String> nompro) {
		this.nompro = nompro;
	}

	public List<String> getNomproTarget() {
		return nomproTarget;
	}

	public void setNomproTarget(List<String> nomproTarget) {
		this.nomproTarget = nomproTarget;
	}

	public List<ProveedorVO> getProveedoresSource() {
		return proveedoresSource;
	}

	public void setProveedoresSource(List<ProveedorVO> proveedoresSource) {
		this.proveedoresSource = proveedoresSource;
	}

	public List<ProveedorVO> getProveedoresTarg() {
		return proveedoresTarg;
	}

	public void setProveedoresTarg(List<ProveedorVO> proveedoresTarg) {
		this.proveedoresTarg = proveedoresTarg;
	}

	public DualListModel<String> getProveedores() {
		return proveedores;
	}

	public void setProveedores(DualListModel<String> proveedores) {
		this.proveedores = proveedores;
	}

	public OfertaVO getOferta() {
		return oferta;
	}

	public void setOferta(OfertaVO oferta) {
		this.oferta = oferta;
	}

	public List<OfertaVO> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertaVO> ofertas) {
		this.ofertas = ofertas;
	}

	@PostConstruct
	public void mostrarOfertas() {
		OfertaBO obo = new OfertaBO();
		HttpSession session = SessionUtils.getSession();
		String usuario = (String) session.getAttribute("username");
		System.out.println("Usuario de la session: " + usuario);
		try {
			ofertas = obo.doSelectOfertas();
			nompro = new ArrayList<String>();
			nomproTarget = new ArrayList<String>();
			proveedores = new DualListModel<String>(nompro, nomproTarget);
			
			compersonalesTarg = new ArrayList<String>();
			compersonalesTarg.add("vacio");
			compersonalesSource = new ArrayList<String>();
			compersonalesSource.add("vacio");
			comperBack = new DualListModel<String>(compersonalesSource, compersonalesTarg);
			estado = new HashMap<String, String>();
			disable=true;
			candidatos = new ArrayList<CandidatoVO>();
			candidatos.add(new CandidatoVO());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void mostrarProveedores() {
		OfertaBO obo = new OfertaBO();
		ProveedorBO pbo = new ProveedorBO();
		CompetenciaBO cbo = new CompetenciaBO();
		CandidatoBO cabo = new CandidatoBO();
		try {
			System.out.println(oferta.getIdoferta());
			nomproTarget = pbo.doProveedorOferta(oferta.getIdoferta());
			nompro = pbo.doProveedorMenosOferta(oferta.getIdoferta());
			compTencicas = obo.getCompetenciaByIdOferta(oferta.getIdoferta(), oferta.getPuesto());
			compersonalesTarg = cbo.getCompetenciasPersonalesByIdOferta(oferta.getIdoferta(), 't');
			compersonalesSource = cbo.getCompetenciasPersonalesByIdOferta(oferta.getIdoferta(), 's');
			comperBack = new DualListModel<String>(compersonalesSource, compersonalesTarg);
			
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		candidatos = cabo.doGetCandidatosOferta(oferta.getIdoferta());

		proveedores = new DualListModel<String>(nompro, nomproTarget);
	}

	

	public void oyenteAjax(AjaxBehaviorEvent evento) {
		oferta = (OfertaVO) evento.getComponent().getAttributes().get("ofert");
	
		try {
			mostrarProveedores();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String buttonModifica(AjaxBehaviorEvent evento1) {
		OfertaBO obo = new OfertaBO();
			     
		oferta = (OfertaVO) evento1.getComponent().getAttributes().get("modoferta");
	
		oferta.getPerfil();
		if(oferta.getEstado().equals(OfertaDAO.ESTADO_OFERTA_ABIERTO)){
		for(String p : proveedores.getTarget()){
			
			int idproveedor =Integer.parseInt(p.substring(0,p.indexOf("-")));
			obo.actualizarOfertaProveedor(idproveedor, oferta.getIdoferta());
		}
		
		for(String cp : comperBack.getTarget()){
			System.out.println("Proveedor: "+cp);
			int idcompetencia =Integer.parseInt(cp.substring(0,cp.indexOf("-")));
			obo.actualizarOfertaComPersonal(idcompetencia, oferta.getIdoferta());
		}
		}
		
		return "modoferta";
	}

	public String buttonValidar(AjaxBehaviorEvent evento1) {
		OfertaBO obo = new OfertaBO();		
		
		oferta = (OfertaVO) evento1.getComponent().getAttributes().get("valOferta");
		
		System.out.println("Oferta Validando: "+oferta.getIdoferta()+"-"+oferta.getPerfil());
		if(oferta.getEstado().equals(OfertaDAO.ESTADO_OFERTA_ABIERTO)){
			obo.cambiarEstado(oferta.getIdoferta(), OfertaDAO.ESTADO_OFERTA_VALIDADO);
		}
		
//		oferta.setEstado(OfertaDAO.ESTADO_OFERTA_VALIDADO);
				
		return "modoferta";
	}
	private UIComponent mybutton;
	
	public UIComponent getMybutton() {
		return mybutton;
	}

	public void setMybutton(UIComponent mybutton) {
		this.mybutton = mybutton;
	}

	public String mensajeValida(){
		
		String mensajeVal = "";
		if(oferta.getEstado().equals(OfertaDAO.ESTADO_OFERTA_ABIERTO)){
			mensajeVal="La oferta ha camciado de estado 'abierto' a estado: validado";
		}else{
			mensajeVal="La oferta no puede cambiar de estado porque se encuentra en estado: "+oferta.getEstado();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensajeVal, null);
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, msg);
		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cambio de Estado"));
		return "";
		
	}
	
	public void mensajeModifica(){
		String mensajeMod = "";
		System.out.println(oferta.getEstado().equals(OfertaDAO.ESTADO_OFERTA_ABIERTO));
		if(oferta.getEstado().equals(OfertaDAO.ESTADO_OFERTA_ABIERTO)){
			mensajeMod = "La ofeta "+oferta.getIdoferta()+" ha sido modificada";
		}else{
			mensajeMod = "La oferta no puede ser modifica ya se encuentra en estado "+oferta.getEstado();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensajeMod, null);
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, msg);
	}
	public String buttonCerrar(AjaxBehaviorEvent evento1) throws Exception {
		OfertaBO obo = new OfertaBO();
		CandidatoBO cbo = new CandidatoBO();
		ProveedorBO pbo = new ProveedorBO();
		oferta = (OfertaVO) evento1.getComponent().getAttributes().get("cerrarOferta");
		System.out.println("Candidato Seleccionado:"+selectCandidato.getIdcandidato());
		System.out.println("Cerrar Oferta: "+oferta.getIdoferta());
		int valor = cbo.doSelectedCandidato(oferta.getIdoferta(), selectCandidato.getIdcandidato());
		
			//avisar al proveedor
			String emailTo = pbo.doGetEmailProveedor(selectCandidato.getIdproveedor());
			System.out.println(emailTo);
			String msn = "Tu candidato "+selectCandidato.getNombrecandidato()+" ha sido seleccionado para la oferta "+oferta.getIdoferta()+"-"+oferta.getPuesto();
			System.out.println(msn);
			String subject = "Selección Candidato - Oferta "+oferta.getIdoferta()+"-"+oferta.getPuesto();
			System.out.println(subject);
			pbo.envioCorreo(emailTo, msn, subject);
			
			pbo.envioCorreoNoSelected(oferta.getIdoferta(), selectCandidato.getIdcandidato(), selectCandidato.getIdproveedor());
		
			obo.cambiarEstado(oferta.getIdoferta(), OfertaDAO.ESTADO_OFERTA_CERRADO);
			oferta.setEstado(OfertaDAO.ESTADO_OFERTA_CERRADO);
			
			return "modoferta";
	}
	
	public void actulizarOferta() {
		System.out.println("Nuevos Estudios: " + oferta.getPerfil());
		FacesContext ctx = FacesContext.getCurrentInstance();
	}
}
