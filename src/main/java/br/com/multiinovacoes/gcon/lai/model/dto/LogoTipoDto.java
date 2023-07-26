package br.com.multiinovacoes.gcon.lai.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class LogoTipoDto {
	
	
	@ApiModelProperty(value = "Id do logotipo")
	private Long id;
	
	@ApiModelProperty(value = "órgão do logotipo")
	private Long orgao;
	
	@ApiModelProperty(value = "Nome do logotipo")
	private String nome;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
