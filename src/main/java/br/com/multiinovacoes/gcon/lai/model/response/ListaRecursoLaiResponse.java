package br.com.multiinovacoes.gcon.lai.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.lai.model.dto.RecursoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista RecursoLaiDto Response")
public class ListaRecursoLaiResponse {
	
	@ApiModelProperty(value = "Lista de Atendimentos")
	private List<RecursoLaiDto> recursoLaiDtoList;
	
	public ListaRecursoLaiResponse(List<RecursoLaiDto> recursoLaiDtoList) {
		this.recursoLaiDtoList = recursoLaiDtoList;
	}

	public List<RecursoLaiDto> getRecursoDtoList() {
		return recursoLaiDtoList;
	}

	public void setRecursoDtoList(List<RecursoLaiDto> recursoLaiDtoList) {
		this.recursoLaiDtoList = recursoLaiDtoList;
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
