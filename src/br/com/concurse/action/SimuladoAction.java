package br.com.concurse.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.concurse.bean.QuestaoBean;
import br.com.concurse.entity.Assunto;
import br.com.concurse.manager.AssuntoManager;
import br.com.concurse.manager.PerguntaManager;
import br.com.concurse.manager.QuestaoManager;
import br.com.concurse.manager.RespostaManager;

@ViewScoped
@ManagedBean(name = SimuladoAction.NAME)
public class SimuladoAction extends AbstractAction implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "simuladoAction";
	
	private Logger log = Logger.getLogger(SimuladoAction.class);
	private QuestaoManager questaoManager;
	private AssuntoManager assuntoManager;
	private PerguntaManager perguntaManager;
	private RespostaManager respostaManager;
	private Assunto assunto;
	private List<Assunto> listAssuntos = new ArrayList<Assunto>();
	private List<QuestaoBean> questoes;
	
	public void gerarSimulado(){
		questoes = new ArrayList<QuestaoBean>();
		questoes.addAll(getQuestaoManager().gerarSimulado(this.assunto.getIdAssunto()));
	}
	
	public boolean renderizaSimulado(){
		if (questoes != null) {
			return questoes.size() > 0;
		}
		return false;
	}
	
	public void newInstance(){
		new SimuladoAction();
		assunto = new Assunto();
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
	
	public Assunto getAssunto() {
		if (assunto == null) {
			assunto = new Assunto();
		}
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
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

	public List<QuestaoBean> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<QuestaoBean> questoes) {
		this.questoes = questoes;
	}
	
	public QuestaoManager getQuestaoManager() {
		if (questaoManager == null) {
			return new QuestaoManager();
		}
		return questaoManager;
	}
}
