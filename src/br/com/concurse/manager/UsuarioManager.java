package br.com.concurse.manager;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import br.com.concurse.dao.UsuarioDAO;
import br.com.concurse.entity.Usuario;

public class UsuarioManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
 
    public void createUsuario(Usuario usuario) {
        usuarioDAO.beginTransaction();
        usuarioDAO.save(usuario);
        usuarioDAO.commitAndCloseTransaction();
    }
 
    public void updateUsuario(Usuario usuario) {
        usuarioDAO.beginTransaction();
		//Usuario persistedUsuario = usuarioDAO.find(usuario.getId());
		//persistedUsuario.setAge(usuario.getAge());
		//persistedUsuario.setName(usuario.getName());
        usuarioDAO.update(usuario);
        usuarioDAO.commitAndCloseTransaction();
    }
 
    public Usuario findUsuario(int usuarioId) {
        usuarioDAO.beginTransaction();
        Usuario usuario = usuarioDAO.find(usuarioId);
        usuarioDAO.closeTransaction();
        return usuario;
    }
 
    public List<Usuario> listAll() {
        usuarioDAO.beginTransaction();
        List<Usuario> result = usuarioDAO.findAll();
        usuarioDAO.closeTransaction();
        return result;
    }
 
    public void deleteUsuario(Usuario usuario) {
        usuarioDAO.beginTransaction();
        Usuario persistedUsuario = usuarioDAO.findReferenceOnly(usuario.getIdUsuario());
        usuarioDAO.delete(persistedUsuario);
        usuarioDAO.commitAndCloseTransaction();
    }
    
    public Usuario usuarioValido(String login, String senha){
    	return usuarioDAO.usuarioValido(login, senha);
    }
}
