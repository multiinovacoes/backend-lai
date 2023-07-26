package br.com.multiinovacoes.gcon.lai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.multiinovacoes.gcon.lai.model.Anexo;

public interface AnexoRepository extends JpaRepository<Anexo, Long> {
	
	public List<Anexo> findByAtendimento(Long atendimento);

	@Query("SELECT coalesce(max(a.id), 0) FROM Anexo a")
	public Long getMaxSequencialId();


}
