package br.com.concurse.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.consurse.annotations.ChildList;
import br.com.consurse.annotations.PathDescriptor;

@Entity
@Table(name="tb_assunto")
public class Assunto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idAssunto;
	private Assunto assuntoSuperior;
	private String assunto;
	private Date dataCadastro;
	private List<Assunto> assuntoList = new ArrayList<Assunto>(0);

	public Assunto() {
	}
	
	@SequenceGenerator(name = "TB_ASSUNTO_GENERATOR", sequenceName = "sq_tb_assunto")
	@Id
	@GeneratedValue(generator="TB_ASSUNTO_GENERATOR")
	@Column(name = "id_assunto", unique = true, nullable = false)
	public int getIdAssunto() {
		return this.idAssunto;
	}

	public void setIdAssunto(int idAssunto) {
		this.idAssunto = idAssunto;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_assunto_superior")
	public Assunto getAssuntoSuperior() {
		return this.assuntoSuperior;
	}

	public void setAssuntoSuperior(Assunto assuntoSuperior) {
		this.assuntoSuperior = assuntoSuperior;
	}

	@Column(name = "ds_assunto", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	@PathDescriptor
	public String getAssunto() {
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
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

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "assuntoSuperior")
	@ChildList
	public List<Assunto> getAssuntoList() {
		return this.assuntoList;
	}

	public void setAssuntoList(List<Assunto> assuntoList) {
		this.assuntoList = assuntoList;
	}

	
	@Override
	public String toString() {
		return assunto;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Assunto)) {
			return false;
		}
		Assunto other = (Assunto) obj;
		if (getIdAssunto() != other.getIdAssunto()) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return idAssunto;
	}
}
