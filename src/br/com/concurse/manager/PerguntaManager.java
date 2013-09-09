package br.com.concurse.manager;

import java.io.Serializable;
import java.util.List;

import br.com.concurse.dao.PerguntaDAO;
import br.com.concurse.entity.Pergunta;

public class PerguntaManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private PerguntaDAO perguntaDAO = new PerguntaDAO();
 
    public void createPergunta(Pergunta pergunta) {
        perguntaDAO.beginTransaction();
        perguntaDAO.save(pergunta);
        perguntaDAO.commitAndCloseTransaction();
    }
 
    public void updatePergunta(Pergunta pergunta) {
        perguntaDAO.beginTransaction();
		//Pergunta persistedPergunta = perguntaDAO.find(pergunta.getId());
		//persistedPergunta.setAge(pergunta.getAge());
		//persistedPergunta.setName(pergunta.getName());
        perguntaDAO.update(pergunta);
        perguntaDAO.commitAndCloseTransaction();
    }
 
    public Pergunta findPergunta(int perguntaId) {
        perguntaDAO.beginTransaction();
        Pergunta pergunta = perguntaDAO.find(perguntaId);
        perguntaDAO.closeTransaction();
        return pergunta;
    }
 
    public List<Pergunta> listAll() {
        perguntaDAO.beginTransaction();
        List<Pergunta> result = perguntaDAO.findAll();
        perguntaDAO.closeTransaction();
        return result;
    }
 
    public void deletePergunta(Pergunta pergunta) {
        perguntaDAO.beginTransaction();
        Pergunta persistedPergunta = perguntaDAO.findReferenceOnly(pergunta.getIdPergunta());
        perguntaDAO.delete(persistedPergunta);
        perguntaDAO.commitAndCloseTransaction();
    }
}
