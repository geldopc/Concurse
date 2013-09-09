package br.com.concurse.manager;

import java.io.Serializable;
import java.util.List;

import br.com.concurse.dao.RespostaDAO;
import br.com.concurse.entity.Resposta;

public class RespostaManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private RespostaDAO respostaDAO = new RespostaDAO();
 
    public void createResposta(Resposta resposta) {
        respostaDAO.beginTransaction();
        respostaDAO.save(resposta);
        respostaDAO.commitAndCloseTransaction();
    }
 
    public void updateResposta(Resposta resposta) {
        respostaDAO.beginTransaction();
		//Resposta persistedResposta = respostaDAO.find(resposta.getId());
		//persistedResposta.setAge(resposta.getAge());
		//persistedResposta.setName(resposta.getName());
        respostaDAO.update(resposta);
        respostaDAO.commitAndCloseTransaction();
    }
 
    public Resposta findResposta(int respostaId) {
        respostaDAO.beginTransaction();
        Resposta resposta = respostaDAO.find(respostaId);
        respostaDAO.closeTransaction();
        return resposta;
    }
 
    public List<Resposta> listAll() {
        respostaDAO.beginTransaction();
        List<Resposta> result = respostaDAO.findAll();
        respostaDAO.closeTransaction();
        return result;
    }
 
    public void deleteResposta(Resposta resposta) {
        respostaDAO.beginTransaction();
        Resposta persistedResposta = respostaDAO.findReferenceOnly(resposta.getIdResposta());
        respostaDAO.delete(persistedResposta);
        respostaDAO.commitAndCloseTransaction();
    }
}
