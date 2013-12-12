package br.com.concurse.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.concurse.bean.QuestaoBean;
import br.com.concurse.dao.PerguntaDAO;
import br.com.concurse.dao.QuestaoDAO;
import br.com.concurse.dao.RespostaDAO;
import br.com.concurse.entity.Pergunta;

public class QuestaoManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private QuestaoDAO questaoDAO = new QuestaoDAO();
    private PerguntaDAO perguntaDAO = new PerguntaDAO();
    private RespostaDAO respostaDAO = new RespostaDAO();
 
    public void createPergunta(Pergunta questao) {
        questaoDAO.beginTransaction();
        questaoDAO.save(questao);
        questaoDAO.commitAndCloseTransaction();
    }
 
    public void updatePergunta(Pergunta questao) {
        questaoDAO.beginTransaction();
		//Pergunta persistedPergunta = questaoDAO.find(questao.getId());
		//persistedPergunta.setAge(questao.getAge());
		//persistedPergunta.setName(questao.getName());
        questaoDAO.update(questao);
        questaoDAO.commitAndCloseTransaction();
    }
 
    public Pergunta findPergunta(int questaoId) {
        questaoDAO.beginTransaction();
        Pergunta questao = questaoDAO.find(questaoId);
        questaoDAO.closeTransaction();
        return questao;
    }
 
    public List<Pergunta> listAll() {
        questaoDAO.beginTransaction();
        List<Pergunta> result = questaoDAO.findAll();
        questaoDAO.closeTransaction();
        return result;
    }
 
    public void deletePergunta(Pergunta questao) {
        questaoDAO.beginTransaction();
        Pergunta persistedPergunta = questaoDAO.findReferenceOnly(questao.getIdPergunta());
        questaoDAO.delete(persistedPergunta);
        questaoDAO.commitAndCloseTransaction();
    }
    
    public List<QuestaoBean> gerarSimulado(int idAssunto){
		List<QuestaoBean> questoes = new ArrayList<QuestaoBean>();
		QuestaoBean questao = null;
		List<Pergunta> listPerg = perguntaDAO.obterPerguntas(idAssunto);
		int num = 0;
    	for (Pergunta pergunta : listPerg) {
			questao = new QuestaoBean();
			questao.setNumQuestao(++num);
			questao.setPergunta(pergunta);
			questao.getRespostas().addAll(respostaDAO.obterRespostas(pergunta.getIdPergunta()));
			questoes.add(questao);
		}
		return questoes;
    }
}
