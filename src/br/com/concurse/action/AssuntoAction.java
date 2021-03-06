package br.com.concurse.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import br.com.concurse.entity.Assunto;
import br.com.concurse.manager.AssuntoManager;

@SessionScoped
@ManagedBean(name = AssuntoAction.NAME)
public class AssuntoAction extends AbstractAction implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "assuntoAction";
	
	private Logger log = Logger.getLogger(AssuntoAction.class);
	
	private AssuntoManager assuntoManager;
	private Assunto assunto;
	private Assunto assuntoSuperior;
	private List<Assunto> listAssuntos = new ArrayList<Assunto>();
	
	public void cadastrarAssunto() {
        try {
        	assunto.setDataCadastro(new Date());
        	assunto.setAssuntoSuperior(assuntoSuperior);
        	getAssuntoManager().createAssunto(assunto);
        	listAssuntos.add(assunto);
            closeDialog();
            displayInfoMessageToUser("Assunto criado com sucesso!");
            log.info("Assunto criado com sucesso!");
            newInstance();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("N�o foi poss�vel cadastrar o assunto");
            log.info("N�o foi poss�vel cadastrar o assunto!");
            e.printStackTrace();
        }
    }
	
	public void newInstance(){
		new AssuntoAction();
		assunto = new Assunto();
		assuntoSuperior = new Assunto();
	}
	
	public List<Assunto> allAssuntos(){
		return getAssuntoManager().listAll();
	}

	public AssuntoManager getAssuntoManager() {
		if (assuntoManager == null) {
			assuntoManager = new AssuntoManager();
		}
		return assuntoManager;
	}

	public void onEdit(RowEditEvent event) {
		try {
			getAssuntoManager().updateAssunto((Assunto) event.getObject());
			FacesMessage msg = new FacesMessage("Assunto Editado", ((Assunto) event.getObject()).getAssunto());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			onCancel(event);
			log.info("N�o foi poss�vel atualizar o assunto.");
		}
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Assunto Cancelado", ((Assunto) event.getObject()).getAssunto());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public Assunto getAssunto() {
		if (assunto == null) {
			assunto = new Assunto();
		}
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public Assunto getAssuntoSuperior() {
		if (assuntoSuperior == null) {
			assuntoSuperior = new Assunto();
		}
		return assuntoSuperior;
	}

	public void setAssuntoSuperior(Assunto assuntoSuperior) {
		this.assuntoSuperior = assuntoSuperior;
	}

	public List<Assunto> getListAssuntos() {
		if (listAssuntos.isEmpty()) {
			listAssuntos.addAll(allAssuntos());
		}
		return listAssuntos;
	}

	public void setListAssuntos(List<Assunto> listAssuntos) {
		this.listAssuntos = listAssuntos;
	}
}
