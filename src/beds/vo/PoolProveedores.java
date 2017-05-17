package beds.vo;
import java.util.List;
public class PoolProveedores {


 
	private final List<ProveedorVO> pnoselec;
	private final List<ProveedorVO> pselec;
	private final String identifier;
	
	public PoolProveedores(final String identifier, final List<ProveedorVO> pnoselec, final List<ProveedorVO> pselec){
		this.identifier=identifier;
		this.pnoselec=pnoselec;
		this.pselec=pselec;
	}

	public List<ProveedorVO> getPnoselec() {
		return pnoselec;
	}

	public List<ProveedorVO> getPselec() {
		return pselec;
	}

	public String getIdentifier() {
		return identifier;
	}
 
	
	
}