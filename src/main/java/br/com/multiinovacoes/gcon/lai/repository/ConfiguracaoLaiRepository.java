package br.com.multiinovacoes.gcon.lai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.multiinovacoes.gcon.lai.model.ConfiguracaoLai;

public interface ConfiguracaoLaiRepository extends JpaRepository<ConfiguracaoLai, Long> {
	
	public ConfiguracaoLai findByOrgao(Long orgao);


}
