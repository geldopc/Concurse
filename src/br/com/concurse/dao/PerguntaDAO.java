package br.com.concurse.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.concurse.entity.Pergunta;

public class PerguntaDAO extends GenericDAO<Pergunta> {

	private static final long serialVersionUID = 1L;

	public PerguntaDAO() {
		super(Pergunta.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pergunta> obterPerguntas(int idAssunto) {
		beginTransaction();
		StringBuilder sbHql = new StringBuilder();
    	sbHql.append("SELECT o FROM Pergunta o ");
    	sbHql.append("WHERE o.idAssunto.idAssunto = :id");
    	Query query = getEntityManager().createQuery(sbHql.toString());
    	query.setParameter("id", idAssunto);
    	query.setMaxResults(10);
    	List<Pergunta> list = query.getResultList();
    	closeTransaction();
		return list;
	}
}


