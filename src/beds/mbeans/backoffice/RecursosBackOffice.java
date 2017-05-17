package beds.mbeans.backoffice;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.model.DualListModel;
import beds.bo.CompetenciaBO;
import beds.bo.OfertaBO;
import beds.bo.ProveedorBO;
import beds.dao.OfertaDAO;
import beds.vo.CompetenciaVO;
import beds.vo.OfertaCompetenciaVO;
import beds.vo.OfertaVO;
import beds.vo.ProveedorVO;

@ManagedBean(name="recursosbo")
@SessionScoped //Ambito de vida
public class RecursosBackOffice implements Serializable{
	
	public void enlace(){
		
		//gom to ir a enlace
	}

	private static final long serialVersionUID = 1L;
	
	private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
	@ManagedProperty(value = "")
	private DualListModel<String> proveedores;
	
	@ManagedProperty(value = "")
	private DualListModel<String> comper;
	
	@ManagedProperty(value = "")
	private List<CompetenciaVO> compeTecnica;
	
	@ManagedProperty(value = "")
	private List<ProveedorVO> proveedoresSource;
	
	@ManagedProperty(value = "")
	private List<ProveedorVO> proveedoresTarg;
	
	@ManagedProperty(value = "")
	private ProveedorVO proveedor = new ProveedorVO();
		
	@ManagedProperty(value = "")
	private OfertaVO oferta = new OfertaVO();
	
	@ManagedProperty(value = "")
	List<String> nompro;
	
	@ManagedProperty(value = "")
	List<String> nomproTarget;
	
	@ManagedProperty(value = "")
	String puesto;
	
	@ManagedProperty(value = "")
	String perfil;
	
	@ManagedProperty(value = "")
	String segundoNivel;
	
	@ManagedProperty(value = "")
	String tercerNivel;
	
	@ManagedProperty(value = "")
	List<String> compersonales;
	
	@ManagedProperty(value = "")
	List<CompetenciaVO> selectedComTecnicas;
	
	@ManagedProperty(value = "")
	List<String> compersonalesTarget;
	
	@ManagedProperty(value = "")
	List<String> roles;
	@ManagedProperty(value = "")
	String rol;
	@ManagedProperty(value = "")
	private CompetenciaVO competencia;
	
    private List<OfertaCompetenciaVO> ofertaCompetencia;
	
	
	public List<OfertaCompetenciaVO> getOfertaCompetencia() {
		return ofertaCompetencia;
	}

	public void setOfertaCompetencia(List<OfertaCompetenciaVO> ofertaCompetencia) {
		this.ofertaCompetencia = ofertaCompetencia;
	}
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public CompetenciaVO getCompetencia() {
		return competencia;
	}

	public void setCompetencia(CompetenciaVO competencia) {
		this.competencia = competencia;
	}

	public List<CompetenciaVO> getSelectedComTecnicas() {
		return selectedComTecnicas;
	}

	public void setSelectedComTecnicas(List<CompetenciaVO> selectedComTecnicas) {
		this.selectedComTecnicas = selectedComTecnicas;
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

	public List<CompetenciaVO> getCompeTecnica() {
		return compeTecnica;
	}

	public void setCompeTecnica(List<CompetenciaVO> compeTecnica) {
		this.compeTecnica = compeTecnica;
	}

	public DualListModel<String> getComper() {
		return comper;
	}

	public void setComper(DualListModel<String> comper) {
		this.comper = comper;
	}

	public List<String> getCompersonalesTarget() {
		return compersonalesTarget;
	}

	public void setCompersonalesTarget(List<String> compersonalesTarget) {
		this.compersonalesTarget = compersonalesTarget;
	}

	public List<String> getCompersonales() {
		return compersonales;
	}

	public void setCompersonales(List<String> compersonales) {
		this.compersonales = compersonales;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
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

	public OfertaVO getOferta() {
		return oferta;
	}

	public void setOferta(OfertaVO oferta) {
		this.oferta = oferta;
	}

	public DualListModel<String> getProveedores() {
		return proveedores;
	}

	public void setProveedores(DualListModel<String> proveedores) {
		this.proveedores = proveedores;
	}

	public ProveedorVO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorVO proveedor) {
		this.proveedor = proveedor;
	}

	public Map<String, Map<String, String>> getData() {
		return data;
	}

	public void setData(Map<String, Map<String, String>> data) {
		this.data = data;
	}

	@PostConstruct
	public void init() throws Exception{
		ProveedorBO pbo = new ProveedorBO();	
		CompetenciaBO cbo = new CompetenciaBO();
		String valor="";
		
		roles = cbo.getAllTipo();
		compersonales = cbo.getCompetenciasPersonales();
		compersonalesTarget = new ArrayList<String>();
		
		oferta = new OfertaVO();
				
		proveedoresSource = pbo.getAllProveedor();
		
		nomproTarget= new ArrayList<String>();
		nompro= new ArrayList<String>();
		for(ProveedorVO p:proveedoresSource){			
			//idpro.add(String.valueOf(p.getIdproveedor()));
			valor =String.valueOf(p.getIdproveedor())+"-"+p.getNombrepro();
			nompro.add(valor);
		}
		
		compeTecnica = cbo.getCompetenciaByTipo("Frontend Developer");
		comper = new DualListModel<String>(compersonales, compersonalesTarget);
		proveedores = new DualListModel<String>(nompro, nomproTarget);
		
	}
	public void cambiarCompTecnica(AjaxBehaviorEvent event) throws Exception{
		
		System.out.println("Llega a este metodo: "+rol);
		CompetenciaBO cbo = new CompetenciaBO();
		compeTecnica = cbo.getCompetenciaByTipo(rol);
		//compeTecnica = cbo.getCompetenciaByTipo(r);
	}
	public String guardarOferta() throws Exception{
		
		OfertaBO obo = new OfertaBO();
		System.out.println("Comenzaremos a crear la oferta");
	
		
		Date fecha = new Date();
		String fechacreacion = DateFormat.getInstance().format(fecha);
		System.out.println("Fecha: "+fechacreacion);
		
		int idoferta = obo.doCreateOferta(rol, perfil, fechacreacion);
		int idproveedor = 0;
		for(String s:proveedores.getTarget()){
			//System.out.println("Proveedor: "+s);
			idproveedor =Integer.parseInt(s.substring(0,s.indexOf("-")));
			//System.out.println("Proveedor: "+idproveedor);	
			obo.doCreateOferta(idoferta, idproveedor);
		}
		for(CompetenciaVO competencia:selectedComTecnicas){
			System.out.println(competencia.getIdcompetencia());
			//Aqui faltarian los campos de segundo y tercer nivel
			obo.doCreateOfertaCompetencia(idoferta, competencia.getIdcompetencia(), segundoNivel, tercerNivel);
		}
		int idcp = 0;
		for(String cp:comper.getTarget()){
			System.out.println("Compe Perso: "+cp);
			idcp = Integer.parseInt(cp.substring(0,cp.indexOf("-")));
			obo.doCreateOfertaCompetencia(idoferta, idcp, segundoNivel, tercerNivel);
		}
		for(OfertaCompetenciaVO ae: ofertaCompetencia){
			System.out.println("Años de Experiencia: "+ae.getExperiencia());
		}
				
		return "backoffice";
	
	}
	
	public void getProveedorByID(int id) throws Exception{
		ProveedorBO pbo = new ProveedorBO();
		proveedor = pbo.getProveedorById(id);
	}
	
	public void oyenteNuevaOferta(AjaxBehaviorEvent evento) throws Exception{
		/*perfil = (String) evento.getComponent().getAttributes().get("perfil");
		segundoNivel = (String) evento.getComponent().getAttributes().get("segNivel");
		tercerNivel = (String) evento.getComponent().getAttributes().get("terNivel");*/
				System.out.println("Perfil: "+perfil);
				System.out.println("Segundo Nivel: "+segundoNivel);
				System.out.println("Tercer Nivel: "+tercerNivel);
		guardarOferta();	
		
	}
	
	public void mensajeNewOferta(){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha creado una nueva oferta");
		context.addMessage(null, msg);
		
	}
	


	
	
}
