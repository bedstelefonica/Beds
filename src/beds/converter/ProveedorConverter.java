package beds.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import beds.vo.ProveedorVO;




@FacesConverter("teamPickListConverter")
public class ProveedorConverter implements Converter{
	
		 
		

		@Override
		public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
			return value;
			
			
		}


		@Override
		public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
			// TODO Auto-generated method stub
			return null;
		}
}
