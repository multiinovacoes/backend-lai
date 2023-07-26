package br.com.multiinovacoes.gcon.lai.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class TipoDocumentoDto {
	
	@ApiModelProperty(value = "Id do Tipo Documento")	
	private Long id;

	@ApiModelProperty(value = "Descrição do Tipo Documento")
	private String descricao;

	@ApiModelProperty(value = "Mascara do Tipo Documento")
	private String mascara;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
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
