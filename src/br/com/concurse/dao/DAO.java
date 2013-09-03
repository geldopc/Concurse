package br.com.concurse.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.concurse.util.JPAUtil;

public class DAO<T> {
	private Class<T> classe;
	public static EntityManager entityManager;
	
	static{
		entityManager = JPAUtil.getEntityManager();
	}

	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	public void adicionar(T obj) {
		entityManager.getTransaction().begin();
		entityManager.persist(obj);
		entityManager.getTransaction().commit();
	}

	public void remover(T obj) {
		entityManager.getTransaction().begin();
		entityManager.remove(obj);
		entityManager.getTransaction().commit();
	}	
	
	public T buscar(Object id) {
		return entityManager.find(classe, id);
	}
	
	public void atualizar(T obj) {
		entityManager.getTransaction().begin();
		entityManager.merge(obj);
		entityManager.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<T> listar() {
		return entityManager.createQuery("from " + classe.getSimpleName()).getResultList();
	}	
}
