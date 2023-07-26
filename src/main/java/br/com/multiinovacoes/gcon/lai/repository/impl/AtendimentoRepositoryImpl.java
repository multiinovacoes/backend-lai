package br.com.multiinovacoes.gcon.lai.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.multiinovacoes.gcon.lai.model.Atendimento;
import br.com.multiinovacoes.gcon.lai.repository.AtendimentoQueries;

public class AtendimentoRepositoryImpl implements AtendimentoQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Atendimento consultaAtendimento(Long codigoAtendimento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteria = builder.createQuery(Atendimento.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);
		Predicate predicates = builder.equal(root.get("id"), codigoAtendimento);
		criteria.where(predicates);
		TypedQuery<Atendimento> query = manager.createQuery(criteria);
		
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}

	
	@Override
	public Atendimento pesquisarNumeroProtocolo(String numeroProtocolo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteria = builder.createQuery(Atendimento.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);
		Predicate predicates = builder.equal(root.get("numeroProtocolo"), numeroProtocolo);
		criteria.where(predicates);
		TypedQuery<Atendimento> query = manager.createQuery(criteria);
		
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}
	

	@Override
	@Transactional
	public Page<Atendimento> consultaNovasManifestacoes(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT a FROM Atendimento a where a.orgao = 2 and a.status <> 2 "
				+ "and a.statusAtendimento = 0 and (a.dataConclusao = '1969-12-31 21:00:00.000' or a.dataConclusao is null)"
				+ "and (select count(e.id) from Encaminhamento e where e.atendimento = a.id and e.status = 0) = 0";
		TypedQuery<Atendimento> query = manager.createQuery(q, Atendimento.class);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, totalRegistros);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Atendimento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	@Override
	public String consultar(String campo, Long id, Long orgao) {
		Query query =  manager.createNativeQuery("SELECT " + campo + " FROM OUVIDORIA_ATENDIMENTO WHERE INATENDIMENTOID = ? AND INCODIGOORGAO = ? ");
		query.setParameter(1, id);
		query.setParameter(2, orgao);
	    @SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
        if (!lista.isEmpty()) {
        	return (String )lista.get(0);
        }
        else
        	return "Não encontrado";
	}


	@Override
	public Integer qtdHistoricoUsuario(long codigoOrgao, long tipoDocumento, String documento, String email, long numero) {
		  String sql = "";
		  sql = " SELECT COUNT(*) FROM OUVIDORIA_ATENDIMENTO (NOLOCK) WHERE INCODIGOORGAO = ? AND INATENDIMENTOID <> ? ";
		  if(documento != null && !documento.equals("") && !documento.equals("Anônimo")){
			  if(email != null && !email.trim().equals("")){
				  sql +=" AND (VAEMAIL LIKE '"+email+"' OR "; 
			  }else{
				  sql+=" AND ( ";
			  }
			  sql +=" (INCODIGOTIPODOCUMENTO = "+tipoDocumento+" AND VACPF LIKE '"+documento+"'))";
		  }else{
			  if(email != null && !email.trim().equals("")){
				  sql +=" AND (VAEMAIL LIKE '"+email+"')";
			  }
		  }
		  
			Query query =  manager.createNativeQuery(sql);
			query.setParameter(1, codigoOrgao);
			query.setParameter(2, numero);
		    @SuppressWarnings("unchecked")
			List<Object> lista = query.getResultList();
	        if (!lista.isEmpty()) {
	        	return (Integer )lista.get(0);
	        }
		return null;
	}
	

}
