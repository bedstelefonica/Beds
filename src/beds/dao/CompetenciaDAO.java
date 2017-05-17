package beds.dao;

import java.util.List;

public interface CompetenciaDAO {
	public List<String> getAllTipo() throws Exception;	

    public List<String> getCompetenciaByTipo(String tipo) throws Exception;
    
    public List<String> getCompetenciasPersonales() throws Exception;	
}
