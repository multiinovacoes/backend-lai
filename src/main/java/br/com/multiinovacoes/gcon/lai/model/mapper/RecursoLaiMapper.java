package br.com.multiinovacoes.gcon.lai.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.multiinovacoes.gcon.lai.model.RecursoLai;
import br.com.multiinovacoes.gcon.lai.model.dto.RecursoLaiDto;
import br.com.multiinovacoes.gcon.lai.model.request.RecursoLaiRequest;

@Mapper(componentModel = "spring")
public interface RecursoLaiMapper {
	

	List<RecursoLaiDto> fromResponseList(List<RecursoLai> list);
	 
	RecursoLaiDto toDto(RecursoLai area);
	 
	RecursoLai fromRecursoLai(RecursoLaiRequest request);
	
	RecursoLai toRecursoLai(RecursoLaiDto dto);   
	



}
