
package br.com.concurse.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.concurse.bean.QuestaoBean;
import br.com.concurse.entity.Assunto;
import br.com.concurse.entity.EstatisticaUsuario;
import br.com.concurse.entity.Usuario;
import br.com.concurse.manager.AssuntoManager;
import br.com.concurse.manager.EstatisticaUsuarioManager;
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
	private EstatisticaUsuarioManager estatisticaUsuarioManager;
	private Assunto assunto;
	private List<Assunto> listAssuntos = new ArrayList<Assunto>();
	private List<QuestaoBean> questoes;
	private boolean exibeResultado = false;
	
	public void gerarSimulado(){
		exibeResultado = false;
		questoes = new ArrayList<QuestaoBean>();
		questoes.addAll(getQuestaoManager().gerarSimulado(this.assunto.getIdAssunto()));
	}
	
	public void corrigirSimulado(){
		for (QuestaoBean q : this.questoes) {
			exibeResultado = true;
			System.err.println(q);
		}
	}
	
	public void gravarSimulado(){
		log.info("Gravando....");
		exibeResultado = false;
		Random rdn = new Random();
		Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		EstatisticaUsuario estatisticaUsuario = getEstatisticaUsuarioManager().obterEstatistica(usuario.getIdUsuario(), assunto.getIdAssunto());
		if (estatisticaUsuario == null) {
			estatisticaUsuario = new EstatisticaUsuario();
		}
		estatisticaUsuario.setUsuario(usuario);
		estatisticaUsuario.setAssunto(assunto);
		estatisticaUsuario.setTotalQuestao(isNewEstatistica(estatisticaUsuario) ? estatisticaUsuario.getTotalQuestao() + 10 : 10 );
		estatisticaUsuario.setQtdAcerto(isNewEstatistica(estatisticaUsuario) ? estatisticaUsuario.getQtdAcerto() + rdn.nextInt(11) : rdn.nextInt(11));
		getEstatisticaUsuarioManager().updateEstatisticaUsuario(estatisticaUsuario);
		questoes.clear();
	}

	private boolean isNewEstatistica(EstatisticaUsuario estatisticaUsuario) {
		return estatisticaUsuario.getIdEstatisticaUsuario() > 0;
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

	public boolean isExibeResultado() {
		return exibeResultado;
	}

	public void setExibeResultado(boolean exibeResultado) {
		this.exibeResultado = exibeResultado;
	}

	public EstatisticaUsuarioManager getEstatisticaUsuarioManager() {
		if (estatisticaUsuarioManager == null) {
			return new EstatisticaUsuarioManager();
		}
		return estatisticaUsuarioManager;
	}

	public void setEstatisticaUsuarioManager(EstatisticaUsuarioManager estatisticaUsuarioManager) {
		this.estatisticaUsuarioManager = estatisticaUsuarioManager;
	}
}
