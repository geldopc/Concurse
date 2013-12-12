package br.com.concurse.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.concurse.dataModel.RespostaDataModel;
import br.com.concurse.entity.Pergunta;
import br.com.concurse.entity.Resposta;

public class QuestaoBean {
	
	private Pergunta pergunta;
	private List<Resposta> respostas = new ArrayList<Resposta>();
	private RespostaDataModel respostaModel;
	private Resposta respostaCorreta;
	private int numQuestao;
	
	public QuestaoBean() {
		respostaModel = new RespostaDataModel(respostas);
	}
	
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
	public int getNumQuestao() {
		return numQuestao;
	}
	public void setNumQuestao(int numQuestao) {
		this.numQuestao = numQuestao;
	}
	public RespostaDataModel getRespostaModel() {
		return respostaModel;
	}
	public void setRespostaModel(RespostaDataModel respostaModel) {
		this.respostaModel = respostaModel;
	}

	public Resposta getRespostaCorreta() {
		return respostaCorreta;
	}

	public void setRespostaCorreta(Resposta respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}
	
}
