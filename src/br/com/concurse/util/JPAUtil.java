package br.com.concurse.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("Concurse");
	}
	
	private JPAUtil() {}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
