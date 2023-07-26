package br.com.multiinovacoes.gcon.lai.model.response;

import br.com.multiinovacoes.gcon.lai.model.dto.ManifestanteDto;
import io.swagger.annotations.ApiModelProperty;

public class ManifestanteResponse {
	
	@ApiModelProperty(value = "Manifestante Dto")
	private ManifestanteDto manifestanteDto;
	
	public ManifestanteResponse(ManifestanteDto manifestanteDto) {
		this.manifestanteDto = manifestanteDto;
	}

	public ManifestanteDto getManifestanteDto() {
		return manifestanteDto;
	}

	public void setManifestanteDto(ManifestanteDto manifestanteDto) {
		this.manifestanteDto = manifestanteDto;
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
