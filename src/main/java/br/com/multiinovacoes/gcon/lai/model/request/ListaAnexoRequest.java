package br.com.multiinovacoes.gcon.lai.model.request;

import java.io.Serializable;
import java.util.List;

import br.com.multiinovacoes.gcon.lai.model.dto.ListaAnexoDto;
import io.swagger.annotations.ApiModelProperty;

public class ListaAnexoRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4313343368793462569L;
	
	@ApiModelProperty(value = "Id do atendimento")
	private Long atendimento;
	
	private List<ListaAnexoDto> listaAnexoDto;
	

	public Long getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Long atendimento) {
		this.atendimento = atendimento;
	}

	public List<ListaAnexoDto> getListaAnexoDto() {
		return listaAnexoDto;
	}

	public void setListaAnexoDto(List<ListaAnexoDto> listaAnexoDto) {
		this.listaAnexoDto = listaAnexoDto;
	}
	
	

}
