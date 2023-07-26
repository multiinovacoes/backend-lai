package br.com.multiinovacoes.gcon.lai.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.lai.model.Anexo;
import br.com.multiinovacoes.gcon.lai.model.dto.AnexoDto;
import br.com.multiinovacoes.gcon.lai.model.request.AnexoRequest;

@Mapper(componentModel = "spring")
public interface AnexoMapper {
	
	
	List<AnexoDto> fromResponseList(List<Anexo> list);
	
	AnexoDto toDto(Anexo anexo);
	 
	AnexoDto fromAnexo(AnexoRequest request);
	
	@Mapping(target = "dataAnexo", ignore = true)
	Anexo toAnexo(AnexoDto dto);


}
