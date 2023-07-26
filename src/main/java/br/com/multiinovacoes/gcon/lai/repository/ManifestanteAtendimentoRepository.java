package br.com.multiinovacoes.gcon.lai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.lai.model.ManifestanteAtendimento;

@Repository
public interface ManifestanteAtendimentoRepository extends JpaRepository<ManifestanteAtendimento, Long> {
	
	@Query("SELECT coalesce(max(m.id), 0) FROM ManifestanteAtendimento m")
	public Long getMaxSequencial();


}
