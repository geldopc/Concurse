package br.com.concurse.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.concurse.entity.Resposta;

public class RespostaDAO extends GenericDAO<Resposta> {

	private static final long serialVersionUID = 1L;

	public RespostaDAO() {
		super(Resposta.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Resposta> obterRespostas(int idPergunta) {
		beginTransaction();
		StringBuilder sbHql = new StringBuilder();
    	sbHql.append("SELECT o FROM Resposta o ");
    	sbHql.append("WHERE o.idPergunta.idPergunta = :id");
    	Query query = entityManager.createQuery(sbHql.toString());
    	query.setParameter("id", idPergunta);
    	query.setMaxResults(5);
    	List<Resposta> list = query.getResultList();
    	closeTransaction();
		return list;
	}
}


