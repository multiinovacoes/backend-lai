package br.com.multiinovacoes.gcon.lai.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "LAI_MANIFESTANTE_ATENDIMENTO")
public class ManifestanteAtendimento implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5005805706536523946L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "in_id_manifestante_atendimento")
	private Long id;
	
	@Column(name = "in_id_manifestante")
	private Long idManifestante;
	
	
	@Column(name = "inatendimentoid")
	private Long idAtendimento;
	
	public ManifestanteAtendimento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdManifestante() {
		return idManifestante;
	}

	public void setIdManifestante(Long idManifestante) {
		this.idManifestante = idManifestante;
	}

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
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
		ManifestanteAtendimento other = (ManifestanteAtendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
