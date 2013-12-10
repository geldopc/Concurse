package br.com.concurse.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import br.com.concurse.dao.AssuntoDAO;
import br.com.concurse.entity.Assunto;

@FacesConverter(value = AssuntoConverter.NAME, forClass = Assunto.class)
public class AssuntoConverter implements Converter {

	public static final String NAME = "assuntoConverter";
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) 
		throws ConverterException {
		if (value != null && !value.equals("") && !value.equals("Selecione...")) {
			Assunto assunto = new Assunto();
			AssuntoDAO dao = new AssuntoDAO();
			assunto = dao.getEntityManager().find(Assunto.class, Integer.parseInt(value));
			return assunto;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object)
		throws ConverterException {
		try {
			if (object != null && object.toString()!= null && object.toString().length() > 0 && !object.equals(new Assunto())) {
				return String.valueOf(((Assunto) object).getIdAssunto());
			}
		} catch (ConverterException e) {
			e.printStackTrace();
		}
		return null;
	}
}
