package br.com.multiinovacoes.gcon.lai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.multiinovacoes.gcon.lai.model.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
	
	public List<Atendimento> findByOrgaoAndDescricaoContainingIgnoreCase(Long orgao, String descricao);
	
	public List<Atendimento> findByOrgao(Long orgao);
	
	@Query("SELECT a FROM Atendimento a WHERE a.descricao LIKE %:descricao%")
	public List<Atendimento> findByDescricao(@Param("descricao") String descricao);

	@Query("SELECT a FROM Atendimento a WHERE a.numeroProtocolo = :numeroProtocolo and a.descricao LIKE %:descricao%")
	public List<Atendimento> findByNumeroDescricao(@Param("numeroProtocolo") String numeroProtocolo, @Param("descricao") String descricao);
	
	public List<Atendimento> findByNumeroProtocolo(String numeroProtocolo);
	
	public List<Atendimento> findByUsuarioCriacao(Long codigoManifestante);
	
	public List<Atendimento> findByUsuarioCriacaoAndStatusAtendimento(Long codigoManifestante, Integer statusAtendimento);
	
	public List<Atendimento> findByOrgaoAndNumeroDocumento(Long orgao, String numeroDocumento);
	
	@Query("SELECT coalesce(max(a.numeroAtendimento), 0) FROM Atendimento a where a.anoAtendimento = ?1 and a.orgao = ?2")
	public Long getMaxNumeroAtendimento(Integer ano, Long orgao);

	@Query("SELECT coalesce(max(a.sequencialOrgao), 0) FROM Atendimento a where a.anoAtendimento = ?1 and a.orgao = ?2")
	public Long getMaxSequencialOrgao(Integer ano, Long orgao);


}
