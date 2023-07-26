package br.com.multiinovacoes.gcon.lai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.lai.model.Feriado;

@Repository
public interface FeriadoRepository extends JpaRepository<Feriado, Long> {
	
	public List<Feriado> findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(Long orgao, String descricao);
	
	public List<Feriado> findByOrgaoOrderByDescricaoAsc(Long orgao);
	
	public List<Feriado> findByOrgaoAndDataFeriado(Long orgao, LocalDate data);	


}
