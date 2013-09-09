package br.com.concurse.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_pergunta")
public class Pergunta implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int idPergunta;
	private Assunto idAssunto;
	private String pergunta;
	private Date dataCadastro;
	
	@SequenceGenerator(name = "TB_PERGUNTA_GENERATOR", sequenceName = "sq_tb_pergunta")
	@Id
	@GeneratedValue(generator="TB_PERGUNTA_GENERATOR")
	@Column(name = "id_pergunta", unique = true, nullable = false)
	public int getIdPergunta() {
		return idPergunta;
	}
	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_assunto", nullable = false)
	@NotNull
	public Assunto getIdAssunto() {
		return this.idAssunto;
	}
	public void setIdAssunto(Assunto idAssunto) {
		this.idAssunto = idAssunto;
	}
	
	
	@Column(name = "ds_pergunta", nullable = false)
	@NotNull
	public String getPergunta() {
		return pergunta;
	}
	
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
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
	
	@Override
	public String toString() {
		return pergunta;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pergunta)) {
			return false;
		}
		Pergunta other = (Pergunta) obj;
		if (getIdPergunta() != other.getIdPergunta()) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getIdPergunta();
		return result;
	}
}
