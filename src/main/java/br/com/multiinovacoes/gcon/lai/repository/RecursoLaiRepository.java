package br.com.multiinovacoes.gcon.lai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.multiinovacoes.gcon.lai.model.RecursoLai;

public interface RecursoLaiRepository extends JpaRepository<RecursoLai, Long> {
	
	public List<RecursoLai> findByAtendimento(Long idPedido);
	
	public List<RecursoLai> findByAtendimentoAndStatus(Long idPedido, Integer status);
	
	public Optional<RecursoLai> findByAtendimentoAndTipo(Long idPedido, Integer tipo);


}
