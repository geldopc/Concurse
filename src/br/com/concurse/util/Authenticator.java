package br.com.concurse.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.concurse.entity.Usuario;
import br.com.concurse.filter.AbstractFilter;
import br.com.concurse.manager.UsuarioManager;
import br.com.concurse.type.RoleEnum;

@ApplicationScoped
@ManagedBean(name="authenticator")
public class Authenticator extends AbstractFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(Authenticator.class);
	public Usuario usuarioLogado;
	private String login;
	private String senha;
	private static final String EXTENSION = "png";
	private String image;
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String logar() throws IOException {
		UsuarioManager manager = new UsuarioManager();
		this.usuarioLogado = manager.usuarioValido(login, Crypto.encodeMD5(senha));
		if (usuarioLogado != null) {
			geraImagemServidor();
			FacesContext context = FacesContext.getCurrentInstance();
			getRequest().getSession().setAttribute("usuarioLogado", usuarioLogado);
			context.getExternalContext().redirect("/Concurse/pages/protected/index.xhtml");
			log.info("Usuário: " + getRequest().getSession().getAttribute("usuarioLogado"));
			log.info("Sessão: " + getRequest().getSession());
			return "/Concurse/pages/protected/index.xhtml";
		}
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser("Verifique seu Login/Senha");
		return null;
	}
	
	public String logOut() throws IOException {
		getRequest().getSession().invalidate();
		new Authenticator();
		setUsuarioLogado(null);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().redirect("/Concurse/pages/public/login.xhtml");
		return "/pages/public/login.xhtml";
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	private void geraImagemServidor() {
		try {
			if (usuarioLogado != null) {
				FacesContext context = FacesContext.getCurrentInstance();
				ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
				String imageUsers = servletContext.getRealPath("/resources/images/usuarios");
				File dirImageUsers = new File(imageUsers);
				if (!dirImageUsers.exists()) {
					dirImageUsers.createNewFile();
				}
				byte[] bytes = usuarioLogado.getFoto();
				FileImageOutputStream imageOutput = 
						new FileImageOutputStream(
								new File(dirImageUsers, usuarioLogado.getNome()+"."+ EXTENSION));
				imageOutput.write(bytes, 0, bytes.length);
				imageOutput.flush();
				imageOutput.close();
				setImage("/resources/images/usuarios/"+ usuarioLogado.getNome()+ "." + EXTENSION);
			}
		} catch (Exception ex) {
			System.out.println("Erro " + ex.getMessage());
		}
	}

	public boolean isAdmin() {
		if (usuarioLogado != null) {
			log.info(usuarioLogado.getPapel().getLabel());
			return RoleEnum.A.equals(usuarioLogado.getPapel());
		}
		return false;
	}

	public boolean isUser() {
		if (usuarioLogado != null) {
			log.info(usuarioLogado.getPapel().getLabel());
			return RoleEnum.U.equals(usuarioLogado.getPapel());
		}
		return false;
	}
	
	public void filtro() throws IOException{
		if (usuarioLogado == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("/Concurse/pages/public/login.xhtml");
		}
	}
	
	public void teste(ActionEvent actionEvent) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Testando!"));  
    } 

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
