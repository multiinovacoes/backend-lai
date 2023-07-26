package br.com.multiinovacoes.gcon.lai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.lai.model.Orgao;

@Repository
public interface OrgaoRepository extends JpaRepository<Orgao, Long> {
	

}
