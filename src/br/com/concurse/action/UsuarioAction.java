package br.com.concurse.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.concurse.entity.Usuario;
import br.com.concurse.manager.UsuarioManager;
import br.com.concurse.type.RoleEnum;
import br.com.concurse.util.Crypto;
import br.com.concurse.util.DateUtil;

@SessionScoped
@ManagedBean(name = UsuarioAction.NAME)
public class UsuarioAction extends AbstractAction implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "usuarioAction";
	
	private Logger log = Logger.getLogger(UsuarioAction.class);
	
	private UsuarioManager usuarioManager;
	
	private Usuario usuario;
	private StreamedContent imagem;
	
	public void createUsuario() {
        try {
        	setarUsuarioDefault();
        	criptografaSenha();
        	getUsuarioManager().createUsuario(usuario);
            closeDialog();
            displayInfoMessageToUser("Usuario criado com sucesso!");
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Não foi possível cadastrar usuario");
            e.printStackTrace();
        }
    }
	
	public void cadastrarUsuario(){
		setarUsuarioDefault();
		usuario.setNome("Geldo");
		usuario.setSobrenome("Pina Costa");
		usuario.setLogin("geldopc");
		usuario.setSenha("geldo10");
		criptografaSenha();
		usuario.setEmail("geldopc@gmail.com");
		usuario.setDataNascimento(DateUtil.passStringToDate("02/12/1986", "dd/MM/yyyy"));
		getUsuarioManager().createUsuario(usuario);
		imagem.getName();
	}
	
	private void setarUsuarioDefault(){
		this.usuario.setAdministrador(false);
		this.usuario.setAtivo(false);
		this.usuario.setDataCadastro(new Date());
		this.usuario.setPapel(RoleEnum.U);
	}
	
	@SuppressWarnings("static-access")
	private void criptografaSenha(){
		Crypto crypto = new Crypto("Concurse");
		this.usuario.setSenha(crypto.encodeMD5(usuario.getSenha()));
		System.err.println(crypto.decodeDES(usuario.getSenha()));
	}
	
	public void downloadImg(FileUploadEvent event) {
		try {
			imagem = new DefaultStreamedContent(event.getFile().getInputstream());
			byte[] foto = event.getFile().getContents();
			this.usuario.setFoto(foto);
		} catch (IOException ex) {
			log.info("______________________");
			log.error(ex.getMessage());
		}
	}
	
	public void newInstance(){
		new UsuarioAction();
		usuario = new Usuario();
		imagem = null;
	}
	
	public UsuarioManager getUsuarioManager() {
		if (usuarioManager == null) {
			usuarioManager = new UsuarioManager();
		}
		return usuarioManager;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}
}
