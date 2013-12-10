package br.com.concurse.validator;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ViewScoped
@ManagedBean(name = InputTextValidator.NAME)
public class InputTextValidator implements Validator, Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "inputTextValidator";
	private static final String MSG_VALIDACAO = "Campo obrigatório.";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)	throws ValidatorException {
		if (component instanceof HtmlInputText) {
			HtmlInputText inputText = (HtmlInputText) component;
			if (inputText.isRequired()) {
				throw new ValidatorException(new FacesMessage(MSG_VALIDACAO));
			}
		}
	}
}
