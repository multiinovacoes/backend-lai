package br.com.multiinovacoes.gcon.lai.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LAI_MANIFESTANTE")
public class Manifestante implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -200309753327003942L;

	
	@Id
	@Column(name = "ID_MANIFESTANTE")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "CODIGO_DOCUMENTO")
	private Long codigoDocumento;
	
	@Column(name = "NUMERO_DOCUMENTO")
	private String numeroDocumento;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "UF")
	private String uf;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "TELEFONE")
	private String telefone;
	
	@Column(name = "ORIGEM_CONTATO")
	private Long origemContato;
	
	@Column(name = "SENHA")
	private String senha;
	
	@Column(name = "TIPO")
	private String tipo;
	
	public Manifestante() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCodigoDocumento() {
		return codigoDocumento;
	}

	public void setCodigoDocumento(Long codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getOrigemContato() {
		return origemContato;
	}

	public void setOrigemContato(Long origemContato) {
		this.origemContato = origemContato;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manifestante other = (Manifestante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
