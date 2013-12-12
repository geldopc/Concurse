package br.com.concurse.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    	sbHql.append("WHERE o.idPergunta in(:ids)");
    	Query query = getEntityManager().createQuery(sbHql.toString());
    	query.setParameter("ids", numeroSorteados(idAssunto));
    	query.setMaxResults(10);
    	List<Pergunta> list = query.getResultList();
    	closeTransaction();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private List<Integer> numeroSorteados(int idAssunto){
		StringBuilder sbHql = new StringBuilder();
    	sbHql.append("SELECT o.idPergunta FROM Pergunta o ");
    	sbHql.append("WHERE o.idAssunto.idAssunto = :id");
    	Query query = getEntityManager().createQuery(sbHql.toString());
    	query.setParameter("id", idAssunto);
    	List<Integer> list = query.getResultList();
    	Set<Integer> sort = new HashSet<Integer>();
    	int numPerg = 10;
    	while (sort.size() < 10) {
    		sort.addAll(sorteiaIdsPerguntas(list, numPerg));
    		numPerg = 10 - sort.size();
    		if (numPerg < 0) {
				break;
			}
		}
    	list = new ArrayList<Integer>(sort);
		return list;
	}

	private List<Integer> sorteiaIdsPerguntas(List<Integer> list, int pergRestantes) {
		Random moeda = new Random();
		List<Integer> sort = new ArrayList<Integer>();
    	for (Integer integer : list) {
			int m = moeda.nextInt(2);
			if (m == 1 && sort.size() < pergRestantes && !sort.contains(integer)) {
				sort.add(integer);
			}
		}
    	return sort;
	}
}


