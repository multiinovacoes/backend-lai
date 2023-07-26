package br.com.multiinovacoes.gcon.lai.model.response;

import br.com.multiinovacoes.gcon.lai.model.dto.RecursoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Recurso Lai Response")
public class RecursoLaiResponse {

	
	@ApiModelProperty(value = "Recurso Lai Dto")
	private RecursoLaiDto recursoLaiDto;
	
	public RecursoLaiResponse(RecursoLaiDto recursoLaiDto) {
		this.recursoLaiDto = recursoLaiDto;
	}

	public RecursoLaiDto getRecursoDto() {
		return recursoLaiDto;
	}

	public void setRecursoDto(RecursoLaiDto recursoLaiDto) {
		this.recursoLaiDto = recursoLaiDto;
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
