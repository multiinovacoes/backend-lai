package br.com.multiinovacoes.gcon.lai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.multiinovacoes.gcon.lai.model.Anexo;

public interface AnexoLaiRepository extends JpaRepository<Anexo, Long> {
	
	public List<Anexo> findByAtendimento(Long atendimento);


}
