package br.com.multiinovacoes.gcon.lai.model.request;


import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.multiinovacoes.gcon.lai.model.dto.ListaAnexoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;


@Api
public class AtendimentoRequest {

	
	@ApiModelProperty(value = "Manter sigilo do atendimento")
	private Boolean manterSigilo;
	
	@ApiModelProperty(value = "Tipo de usuário do atendimento")
	private Long tipoUsuario;

	@ApiModelProperty(value = "Nome solicitante do atendimento")
	private String nomeSolicitante;
	
	@ApiModelProperty(value = "Assunto do endereço do atendimento")
	private Long assunto;
	
	@ApiModelProperty(value = "Tipo de documento do atendimento")
	private Long tipoDocumento;
	
	@ApiModelProperty(value = "Número do documento do atendimento")
	private String numeroDocumento;
	
	@ApiModelProperty(value = "Cep do endereço atendimento")
	private String cep;
	
	@ApiModelProperty(value = "Endereço do atendimento")
	private String endereco;
	
	@ApiModelProperty(value = "Número do endereço  do atendimento")
	private String numero;
	
	@ApiModelProperty(value = "Complemento do endereço do atendimento")
	private String complemento;
	
	@ApiModelProperty(value = "Ud do endereço do atendimento")
	private String uf;
	
	@ApiModelProperty(value = "Bairro do endereço do atendimento")
	private String bairro;
	
	@ApiModelProperty(value = "Municipio do endereço do atendimento")
	private String municipio;
	
	@ApiModelProperty(value = "Email do atendimento")
	private String email;
	
	@ApiModelProperty(value = "DDD celular do atendimento")
	private String dddCelular;
	
	@ApiModelProperty(value = "Fone celular do atendimento")
	private String foneCelular;
	
	@ApiModelProperty(value = "Data de entrada do atendimento")
	private LocalDate dataEntrada;

	@NotNull
	@ApiModelProperty(value = "Descrição oque do atendimento")
	private String descricaoOque;

	@NotNull
	@ApiModelProperty(value = "Natureza do atendimento")
	private Long natureza;
	
	@ApiModelProperty(value = "Protocolo de origem do atendimento")
	private String protocoloOrigem;

	@NotNull
	@ApiModelProperty(value = "Orgao do atendimento")
	private Long orgao;
	
	@NotNull
	@ApiModelProperty(value = "Identificado")
	private String identificado;

	@ApiModelProperty(value = "Modalidade de ensino")
	private Integer modalidadeEnsino;
	
	@ApiModelProperty(value = "Bairro Ocorrencia do atendimento")
	private String bairroOcorrencia;

	@ApiModelProperty(value = "Lista de anexos")
	private List<ListaAnexoDto> listaAnexoDto;

	public Long getAssunto() {
		return assunto;
	}

	public void setAssunto(Long assunto) {
		this.assunto = assunto;
	}

	public String getBairroOcorrencia() {
		return bairroOcorrencia;
	}

	public void setBairroOcorrencia(String bairroOcorrencia) {
		this.bairroOcorrencia = bairroOcorrencia;
	}

	public List<ListaAnexoDto> getListaAnexoDto() {
		return listaAnexoDto;
	}

	public void setListaAnexoDto(List<ListaAnexoDto> listaAnexoDto) {
		this.listaAnexoDto = listaAnexoDto;
	}

	public String getIdentificado() {
		return identificado;
	}

	public void setIdentificado(String identificado) {
		this.identificado = identificado;
	}

	public Integer getModalidadeEnsino() {
		return modalidadeEnsino;
	}

	public void setModalidadeEnsino(Integer modalidadeEnsino) {
		this.modalidadeEnsino = modalidadeEnsino;
	}

	public Boolean getManterSigilo() {
		return manterSigilo;
	}

	public void setManterSigilo(Boolean manterSigilo) {
		this.manterSigilo = manterSigilo;
	}

	public Long getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Long tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public Long getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Long tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}

	public String getFoneCelular() {
		return foneCelular;
	}

	public void setFoneCelular(String foneCelular) {
		this.foneCelular = foneCelular;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDescricaoOque() {
		return descricaoOque;
	}

	public void setDescricaoOque(String descricaoOque) {
		this.descricaoOque = descricaoOque;
	}

	public Long getNatureza() {
		return natureza;
	}

	public void setNatureza(Long natureza) {
		this.natureza = natureza;
	}

	public String getProtocoloOrigem() {
		return protocoloOrigem;
	}

	public void setProtocoloOrigem(String protocoloOrigem) {
		this.protocoloOrigem = protocoloOrigem;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
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
