package br.com.multiinovacoes.gcon.lai.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.lai.model.Atendimento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Atendimento Response")
public class ListaAtendimentoResponse {
	
	@ApiModelProperty(value = "Lista de Atendimentos")
	private List<Atendimento> atendimentoDtoList;
	
	public ListaAtendimentoResponse(List<Atendimento> atendimentoDtoList) {
		this.atendimentoDtoList = atendimentoDtoList;
	}

	public List<Atendimento> getAtendimentoDtoList() {
		return atendimentoDtoList;
	}

	public void setAtendimentoDtoList(List<Atendimento> atendimentoDtoList) {
		this.atendimentoDtoList = atendimentoDtoList;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	


}
