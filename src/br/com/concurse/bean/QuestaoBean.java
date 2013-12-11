package br.com.concurse.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.concurse.entity.Pergunta;
import br.com.concurse.entity.Resposta;

public class QuestaoBean {
	
	private Pergunta pergunta;
	private List<Resposta> respostas = new ArrayList<Resposta>();
	
	public Pergunta getPergunta() {
		return pergunta;
	}
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	public List<Resposta> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
	
}
