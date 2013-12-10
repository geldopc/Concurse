package br.com.concurse.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_resposta")
public class Resposta implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idResposta;
	private Pergunta idPergunta;
	private String resposta;
	private Boolean respostaCorreta;
	
	public Resposta() {
	}
	
	public Resposta(Resposta resposta) {
		this.idResposta = resposta.idResposta;
		this.idPergunta = resposta.idPergunta;
		this.resposta = resposta.resposta;
		this.respostaCorreta = resposta.respostaCorreta;
	}
	
	@SequenceGenerator(name = "TB_RESPOSTA_GENERATOR", sequenceName = "sq_tb_resposta")
	@Id
	@GeneratedValue(generator="TB_RESPOSTA_GENERATOR")
	@Column(name = "id_resposta", unique = true, nullable = false)
	public int getIdResposta() {
		return idResposta;
	}
	public void setIdResposta(int idResposta) {
		this.idResposta = idResposta;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pergunta", nullable = false)
	@NotNull
	public Pergunta getIdPergunta() {
		return this.idPergunta;
	}
	public void setIdPergunta(Pergunta idPergunta) {
		this.idPergunta = idPergunta;
	}
	
	
	@Column(name = "ds_resposta", nullable = false)
	@NotNull
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	@Column(name = "in_resposta", nullable = false)
	@NotNull
	public Boolean getRespostaCorreta() {
		return respostaCorreta;
	}
	public void setRespostaCorreta(Boolean respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}

	@Override
	public String toString() {
		return resposta;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Resposta)) {
			return false;
		}
		Resposta other = (Resposta) obj;
		if (getIdResposta() != other.getIdResposta()) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getIdResposta();
		return result;
	}
}
