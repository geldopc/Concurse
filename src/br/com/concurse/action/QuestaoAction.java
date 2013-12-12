package br.com.concurse.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import br.com.concurse.dataModel.RespostaDataModel;
import br.com.concurse.entity.Assunto;
import br.com.concurse.entity.Pergunta;
import br.com.concurse.entity.Resposta;
import br.com.concurse.manager.AssuntoManager;
import br.com.concurse.manager.PerguntaManager;
import br.com.concurse.manager.RespostaManager;
import br.com.concurse.util.JSFMessageUtil;

@ViewScoped
@ManagedBean(name = QuestaoAction.NAME)
public class QuestaoAction extends AbstractAction implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "questaoAction";
	
	private Logger log = Logger.getLogger(QuestaoAction.class);
	
	private AssuntoManager assuntoManager;
	private PerguntaManager perguntaManager;
	private RespostaManager respostaManager;
	private Assunto assunto;
	private Pergunta pergunta = new Pergunta();
	private Resposta resposta = new Resposta();
	private Resposta respostaCorreta;
	private List<Resposta> respostas;
	private Assunto assuntoSuperior;
	private RespostaDataModel respostaModel;
	private List<Assunto> listAssuntos = new ArrayList<Assunto>();
	
	public QuestaoAction() {
		respostaModel = new RespostaDataModel(respostas);
	}
	
	public void cadastrarPergunta(){
		if (this.respostaCorreta != null) {
			log.info(pergunta);
			for (int i = 0; i < respostas.size() - 1; i++) {
				log.info(respostas.get(i));
			}
			addRespostaCorreta(respostas);
			gravarRespostas(gravarPergunta(), respostas);
			newInstance();
			JSFMessageUtil messageUtil = new JSFMessageUtil();
			messageUtil.sendInfoMessageToUser("Questão Cadastrada com Sucesso.");
		}else{
			JSFMessageUtil messageUtil = new JSFMessageUtil();
			messageUtil.sendErrorMessageToUser("Selecione a resposta correta.");
		}
	}
	
	
	
	private void addRespostaCorreta(List<Resposta> ListResp) {
		for (Resposta resposta : ListResp) {
			if (resposta.getResposta().equals(this.respostaCorreta.getResposta())) {
				resposta.setRespostaCorreta(true);
			}else{
				resposta.setRespostaCorreta(false);
			}
		}
	}

	private Pergunta gravarPergunta(){
		pergunta.setIdAssunto(assunto);
		pergunta.setDataCadastro(new Date());
		getPerguntaManager().createPergunta(pergunta);
		return pergunta;
	}
	
	private void gravarRespostas(Pergunta pergunta, List<Resposta> respostas){
		for (Resposta resposta : respostas) {
			resposta.setIdPergunta(pergunta);
			getRespostaManager().createResposta(resposta);
		}
	}
	
	public void newInstance(){
		new QuestaoAction();
		assunto = new Assunto();
		pergunta = new Pergunta();
		respostaCorreta = null;
		respostas.clear();
	}
	
	public void limparResposta(){
		resposta.setResposta(null);
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
	
	public void adicionarResposta(){
		if (this.respostas == null) {
			respostas = new ArrayList<Resposta>(5);
			respostas.add(new Resposta(resposta));
			resposta.setResposta(null);
		}else{
			respostas.add(new Resposta(resposta));
			resposta.setResposta(null);
		}
		respostaModel = new RespostaDataModel(respostas);
	}
	
	public boolean renderizaFormResposta(){
		if (respostas != null) {
			return respostas.size() < 5;
		}
		return true;
	}

	public void onEdit(RowEditEvent event) {
		try {
			getAssuntoManager().updateAssunto((Assunto) event.getObject());
			FacesMessage msg = new FacesMessage("Assunto Editado", ((Assunto) event.getObject()).getAssunto());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			onCancel(event);
			log.info("Não foi possível atualizar o assunto.");
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

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
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

	public Resposta getRespostaCorreta() {
		return respostaCorreta;
	}

	public void setRespostaCorreta(Resposta respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public PerguntaManager getPerguntaManager() {
		if (perguntaManager == null) {
			return new PerguntaManager();
		}
		return perguntaManager;
	}

	public void setPerguntaManager(PerguntaManager perguntaManager) {
		this.perguntaManager = perguntaManager;
	}

	public RespostaManager getRespostaManager() {
		if (respostaManager == null) {
			return new RespostaManager();
		}
		return respostaManager;
	}

	public void setRespostaManager(RespostaManager respostaManager) {
		this.respostaManager = respostaManager;
	}

	public RespostaDataModel getRespostaModel() {
		return respostaModel;
	}

	public void setRespostaModel(RespostaDataModel respostaModel) {
		this.respostaModel = respostaModel;
	}
}
