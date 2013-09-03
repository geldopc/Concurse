package br.com.concurse.manager;

import java.io.Serializable;
import java.util.List;

import br.com.concurse.dao.AssuntoDAO;
import br.com.concurse.entity.Assunto;

public class AssuntoManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private AssuntoDAO assuntoDAO = new AssuntoDAO();
 
    public void createAssunto(Assunto assunto) {
        assuntoDAO.beginTransaction();
        assuntoDAO.save(assunto);
        assuntoDAO.commitAndCloseTransaction();
    }
 
    public void updateAssunto(Assunto assunto) {
        assuntoDAO.beginTransaction();
		//Assunto persistedAssunto = assuntoDAO.find(assunto.getId());
		//persistedAssunto.setAge(assunto.getAge());
		//persistedAssunto.setName(assunto.getName());
        assuntoDAO.update(assunto);
        assuntoDAO.commitAndCloseTransaction();
    }
 
    public Assunto findAssunto(int assuntoId) {
        assuntoDAO.beginTransaction();
        Assunto assunto = assuntoDAO.find(assuntoId);
        assuntoDAO.closeTransaction();
        return assunto;
    }
 
    public List<Assunto> listAll() {
        assuntoDAO.beginTransaction();
        List<Assunto> result = assuntoDAO.findAll();
        assuntoDAO.closeTransaction();
        return result;
    }
 
    public void deleteAssunto(Assunto assunto) {
        assuntoDAO.beginTransaction();
        Assunto persistedAssunto = assuntoDAO.findReferenceOnly(assunto.getIdAssunto());
        assuntoDAO.delete(persistedAssunto);
        assuntoDAO.commitAndCloseTransaction();
    }
}
