package br.com.concurse.dataModel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.concurse.entity.Resposta;

public class RespostaDataModel extends ListDataModel<Resposta> implements SelectableDataModel<Resposta>{
	
	public RespostaDataModel() {
		// TODO Auto-generated constructor stub
	}
	
	public RespostaDataModel(List<Resposta> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Resposta getRowData(String rowKey) {
		List<Resposta> respostas = (List<Resposta>) getWrappedData();
		for (Resposta resposta : respostas) {
			if (resposta.getResposta().equals(rowKey)) {
				return resposta;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Resposta resposta) {
		return resposta.getResposta();
	}

}
