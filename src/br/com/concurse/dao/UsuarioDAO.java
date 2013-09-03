package br.com.concurse.dao;

import javax.persistence.Query;

import br.com.concurse.entity.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {

	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	public Usuario usuarioValido(String login, String senha){
    	StringBuilder hql = new StringBuilder();
    	hql.append("SELECT o FROM Usuario o ");
    	hql.append("WHERE o.login = :login ");
    	hql.append("AND o.senha = :senha");
    	beginTransaction();
    	Query query = entityManager.createQuery(hql.toString());
    	query.setParameter("login", login);
    	query.setParameter("senha", senha);
    	Usuario usuario = null;
    	if (query.getResultList().size() == 1) {
    		usuario = (Usuario) query.getSingleResult();
		}
    	closeTransaction();
    	return usuario;
    }
}


