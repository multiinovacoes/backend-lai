package br.com.multiinovacoes.gcon.lai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.lai.model.Manifestante;


@Repository
public interface ManifestanteRepository extends JpaRepository<Manifestante, Long> {
	
	public Manifestante findByNumeroDocumentoAndSenha(String numeroDocumento, String senha);
	
	public Optional<Manifestante> findByNumeroDocumento(String documento);
	
	@Query("SELECT m FROM Manifestante m where m.id = ?1")
	public Manifestante getManifestante(Long id);
		
	@Query("SELECT coalesce(max(m.id), 0) FROM Manifestante m")
	public Long getMaxId();


}
