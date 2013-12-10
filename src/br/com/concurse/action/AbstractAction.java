package br.com.concurse.action;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.concurse.util.JSFMessageUtil;

public class AbstractAction {
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
	 
    public AbstractAction() {
        super();
    }
 
    protected void displayErrorMessageToUser(String message) {
        JSFMessageUtil messageUtil = new JSFMessageUtil();
        messageUtil.sendErrorMessageToUser(message);
    }
 
    protected void displayInfoMessageToUser(String message) {
        JSFMessageUtil messageUtil = new JSFMessageUtil();
        messageUtil.sendInfoMessageToUser(message);
    }
 
    protected void closeDialog(){
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
    }
 
    protected void keepDialogOpen(){
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
    }
 
    protected RequestContext getRequestContext(){
        return RequestContext.getCurrentInstance();
    }
    
    protected void addMessage(String msg) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
}
