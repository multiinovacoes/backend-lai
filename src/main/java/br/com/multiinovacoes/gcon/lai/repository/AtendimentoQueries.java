package br.com.multiinovacoes.gcon.lai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.multiinovacoes.gcon.lai.model.Atendimento;

public interface AtendimentoQueries {
	
	
	public String consultar(String campo, Long id, Long orgao);
	
	public Atendimento consultaAtendimento(Long codigoAtendimento);
	
	public Atendimento pesquisarNumeroProtocolo(String numeroProtocolo);
	
	public Page<Atendimento> consultaNovasManifestacoes(Long orgao, Pageable page, Integer totalRegistros);
	
	public Integer qtdHistoricoUsuario(long codigoOrgao, long tipoDocumento, String documento, String email, long numero);
	


}
