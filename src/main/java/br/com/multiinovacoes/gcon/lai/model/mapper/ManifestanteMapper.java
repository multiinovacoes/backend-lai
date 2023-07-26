package br.com.multiinovacoes.gcon.lai.model.mapper;

import org.mapstruct.Mapper;

import br.com.multiinovacoes.gcon.lai.model.Manifestante;
import br.com.multiinovacoes.gcon.lai.model.dto.ManifestanteDto;
import br.com.multiinovacoes.gcon.lai.model.request.ManifestanteRequest;

@Mapper(componentModel = "spring")
public interface ManifestanteMapper {
	
	ManifestanteDto toDto(Manifestante manifestante);
	
	Manifestante toManifestante(ManifestanteDto dto);
	
	ManifestanteDto fromManifestante(ManifestanteRequest request);


}
