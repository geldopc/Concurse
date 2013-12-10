package br.com.concurse.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.concurse.type.RoleEnum;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idUsuario;
	private String nome;
	private String sobrenome;
	private String email;
	private String login;
	private String senha;
	private byte[] foto;
	private boolean ativo;
	private boolean administrador;
	private Date dataNascimento;
	private Date dataCadastro;
	private RoleEnum papel;
	
	@Override
	public int hashCode() {
		return idUsuario;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (idUsuario != other.getIdUsuario())
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.nome + " " + this.sobrenome;
	}
	
	@Id
	@Column(name="id_usuario", unique=true, nullable=false)
	@SequenceGenerator(name="tb_usuario_generator", sequenceName="sq_tb_usuario")
	@GeneratedValue(generator="tb_usuario_generator")
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@NotNull
	@Column(name="ds_nome", nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull
	@Column(name="ds_sobrenome", nullable = false)
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	@NotNull
	@Column(name="ds_email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotNull
	@Column(name="ds_login", nullable = false)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@NotNull
	@Column(name="ds_senha", nullable = false)
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Column(name="im_foto")
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	@NotNull
	@Column(name = "is_ativo", nullable = false)
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@NotNull
	@Column(name="is_administrador", nullable = false)
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento", nullable = false)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_cadastro", nullable = false)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="ds_papel", nullable = false, length = 1)
	public RoleEnum getPapel() {
		return papel;
	}
	public void setPapel(RoleEnum papel) {
		this.papel = papel;
	}
}
