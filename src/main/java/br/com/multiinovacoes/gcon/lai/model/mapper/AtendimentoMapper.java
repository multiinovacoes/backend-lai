package br.com.multiinovacoes.gcon.lai.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.lai.model.Atendimento;
import br.com.multiinovacoes.gcon.lai.model.dto.AtendimentoDto;
import br.com.multiinovacoes.gcon.lai.model.request.AtendimentoRequest;

@Mapper(componentModel = "spring")
public interface AtendimentoMapper {
	

	List<AtendimentoDto> fromResponseList(List<Atendimento> list);
	 
	AtendimentoDto toDto(Atendimento area); 
	 
	@Mapping(target = "anoAtendimento", ignore = true)
	@Mapping(target = "area", ignore = true)
	@Mapping(target = "cidade", ignore = true)
	@Mapping(target = "dataAlteracao", ignore = true)
	@Mapping(target = "dataConclusao", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataPrazo", ignore = true)
	@Mapping(target = "descricao", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "instituicao", ignore = true)
	@Mapping(target = "numeroAtendimento", ignore = true)
	@Mapping(target = "numeroProtocolo", ignore = true)
	@Mapping(target = "observacao", ignore = true)
	@Mapping(target = "origemContato", ignore = true)
	@Mapping(target = "resposta", ignore = true)
	@Mapping(target = "satisfaz", ignore = true)
	@Mapping(target = "sequencialOrgao", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "statusAtendimento", ignore = true)
	@Mapping(target = "telefone", ignore = true)
	@Mapping(target = "usuarioAlteracao", ignore = true)
	@Mapping(target = "usuarioCriacao", ignore = true)
	AtendimentoDto fromAtendimento(AtendimentoRequest request);
	
	Atendimento toAtendimento(AtendimentoDto dto);   
	



}
