package br.com.multiinovacoes.gcon.lai.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.lai.enums.StatusAtendimentoEnum;
import br.com.multiinovacoes.gcon.lai.enums.StatusRecursoEnum;
import br.com.multiinovacoes.gcon.lai.enums.TipoRecursoEnum;
import br.com.multiinovacoes.gcon.lai.mail.Mailer;
import br.com.multiinovacoes.gcon.lai.model.Atendimento;
import br.com.multiinovacoes.gcon.lai.model.ConfiguracaoLai;
import br.com.multiinovacoes.gcon.lai.model.Feriado;
import br.com.multiinovacoes.gcon.lai.model.RecursoLai;
import br.com.multiinovacoes.gcon.lai.model.dto.RecursoLaiDto;
import br.com.multiinovacoes.gcon.lai.model.mapper.RecursoLaiMapper;
import br.com.multiinovacoes.gcon.lai.model.request.RecursoLaiRequest;
import br.com.multiinovacoes.gcon.lai.repository.AtendimentoRepository;
import br.com.multiinovacoes.gcon.lai.repository.ConfiguracaoLaiRepository;
import br.com.multiinovacoes.gcon.lai.repository.FeriadoRepository;
import br.com.multiinovacoes.gcon.lai.repository.RecursoLaiRepository;
import br.com.multiinovacoes.gcon.lai.security.GconSecurity;
import br.com.multiinovacoes.gcon.lai.util.GeradorString;



@Service
@Transactional
public class RecursoLaiService {
	
	
	@Autowired
	private RecursoLaiMapper recursoLaiMapper;
	
	@Autowired
	private RecursoLaiRepository recursoLaiRepository;
	
	@Autowired
	private ConfiguracaoLaiRepository configuracaoLaiRepository;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	private FeriadoRepository feriadoRepository; 
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	GconSecurity gconSecurity;
	
	public LocalDate adicionarDiasUteis(Integer qtdeDiasAcrescentados, Long codigoOrgao, LocalDate data) {
		List<Feriado> feriadoInfoList = null;
		while(qtdeDiasAcrescentados > 0){
			data = data.plusDays(1);
			feriadoInfoList = feriadoRepository.findByOrgaoAndDataFeriado(codigoOrgao, data);
			if (!feriadoInfoList.isEmpty()){
				++qtdeDiasAcrescentados;
			}
			if (data.getDayOfWeek() != DayOfWeek.SATURDAY && data.getDayOfWeek() != DayOfWeek.SUNDAY) {
				--qtdeDiasAcrescentados;
			}
		}
		return data;
	}
	
	public RecursoLaiDto getRecursoLai(Long codigoRecurso) {
		return recursoLaiMapper.toDto(recursoLaiRepository.getById(codigoRecurso));
	}

	 
	public RecursoLaiDto getAutorizaRecurso(Long codigoPedido) {
		ConfiguracaoLai conf = configuracaoLaiRepository.findByOrgao(7l);
		RecursoLaiDto recursoLaiDto = new RecursoLaiDto();
		Optional<Atendimento> atendimento = atendimentoRepository.findById(codigoPedido);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (atendimento.isPresent()) {
			
			List<RecursoLai> recursoLai = recursoLaiRepository.findByAtendimento(codigoPedido);
			
			if (!recursoLai.isEmpty()) {

				if (recursoLai.size() < 2) {
					
					for (RecursoLai recursoLai2 : recursoLai) {
						
						if (recursoLai2.getRespostaRecurso() == null) {
							recursoLaiDto.setRecursoAutorizado(false);
							recursoLaiDto.setMensagem("Abertura de recurso não permitida. O recurso de 1ª instância ainda não foi respondido!");
						}else {
							LocalDate dataLimiteRecurso = this.adicionarDiasUteis(conf.getQtdDiasPermitirAbrirRecurso(), conf.getOrgao(), recursoLai2.getDataResposta().toLocalDate());
							if (dataLimiteRecurso.isAfter(LocalDate.now()) || dataLimiteRecurso.compareTo(LocalDate.now()) == 0) {
								recursoLaiDto.setDataPrazo(this.adicionarDiasUteis(conf.getQtdDiasVencerRecurso(), conf.getOrgao(), LocalDate.now()));
								recursoLaiDto.setDataRecurso(LocalDateTime.now());
								recursoLaiDto.setDataResposta(null);
								recursoLaiDto.setJustificativa(null);
								recursoLaiDto.setRecursoAutorizado(true);
								recursoLaiDto.setAtendimento(codigoPedido);
								recursoLaiDto.setRespostaRecurso(null);
								recursoLaiDto.setStatus(StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo());
								recursoLaiDto.setTipo(TipoRecursoEnum.RECURSO_2_INSTANCIA.getCodigo());
							}else {
								recursoLaiDto.setRecursoAutorizado(false);
								recursoLaiDto.setMensagem("Abertura de recurso não permitida. O prazo para abertura de recurso foi expirado em: " + dataLimiteRecurso.format(formatter));
							}
						}
					}
				}else {
					recursoLaiDto.setRecursoAutorizado(false);
					recursoLaiDto.setMensagem("Abertura de recurso não permitida. Todas as instâncias recursais para este pedido foram utilizadas!");
				}
			}else {
				LocalDate dataLimiteRecurso = LocalDate.now();
				if (atendimento.get().getStatusAtendimento().equals(StatusAtendimentoEnum.STATUS_RESOLVIDO.getCodigo())) {
					dataLimiteRecurso = this.adicionarDiasUteis(conf.getQtdDiasPermitirAbrirRecurso(), conf.getOrgao(), atendimento.get().getDataConclusao().toLocalDate());
					recursoLaiDto.setRecursoAutorizado(true);
				}else{
					if (atendimento.get().getDataPrazo().isBefore(LocalDate.now())) {
						dataLimiteRecurso = this.adicionarDiasUteis(conf.getQtdDiasPermitirAbrirRecurso(), conf.getOrgao(), atendimento.get().getDataPrazo());
						recursoLaiDto.setRecursoAutorizado(true);
					}else {
						recursoLaiDto.setRecursoAutorizado(false);
						recursoLaiDto.setMensagem("Abertura de recurso não permitda. O pedido de informação ainda está dentro do prazo previsto de resposta!");
					}
				} 
				if (recursoLaiDto.getRecursoAutorizado()) {
					if (dataLimiteRecurso.isAfter(LocalDate.now()) || dataLimiteRecurso.compareTo(LocalDate.now()) == 0) {
						recursoLaiDto.setDataPrazo(this.adicionarDiasUteis(conf.getQtdDiasVencerRecurso(), conf.getOrgao(), LocalDate.now()));
						recursoLaiDto.setDataRecurso(LocalDateTime.now());
						recursoLaiDto.setDataResposta(null);
						recursoLaiDto.setJustificativa(null);
						recursoLaiDto.setAtendimento(codigoPedido);
						recursoLaiDto.setRespostaRecurso(null);
						recursoLaiDto.setStatus(StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo());
						recursoLaiDto.setTipo(TipoRecursoEnum.RECURSO_1_INSTANCIA.getCodigo());
					}else {
						recursoLaiDto.setRecursoAutorizado(false);
						recursoLaiDto.setMensagem("Abertura de recurso não permitida. O prazo para abertura de recurso foi expirado em: " + dataLimiteRecurso.format(formatter));
					}
				}
			}
		}
		return recursoLaiDto;
	}

	
	
	public RecursoLaiDto salvarRecursoLai(RecursoLaiRequest recursoLaiRequest) {
			ConfiguracaoLai conf = configuracaoLaiRepository.findByOrgao(7l);
			RecursoLai recursoLai = recursoLaiMapper.fromRecursoLai(recursoLaiRequest);
		    recursoLai.setDataPrazo(adicionarDiasUteis(conf.getQtdDiasVencerRecurso(), conf.getOrgao(), LocalDate.now()));
		    recursoLai.setDataRecurso(LocalDateTime.now());
		    recursoLai.setDataResposta(null);
		    recursoLai.setIdUsuarioResposta(null);
		    recursoLai.setRespostaRecurso(null);
		    recursoLai.setStatus(StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo());
		    
		    RecursoLaiDto recursolai_Instancia_1 = null;
		    
		    if (recursoLai.getTipo().equals(TipoRecursoEnum.RECURSO_2_INSTANCIA.getCodigo())) {
			    recursolai_Instancia_1 = recursoLaiMapper.toDto(recursoLaiRepository.findByAtendimentoAndTipo(recursoLai.getAtendimento(), TipoRecursoEnum.RECURSO_1_INSTANCIA.getCodigo()).get());
		    	recursoLai.setParametro(GeradorString.getRandomString());
		    }
		    
		    recursoLaiRepository.save(recursoLai);
		    
		    Optional<Atendimento> atendimento = atendimentoRepository.findById(recursoLai.getAtendimento());

		    String cabecalho_texto = null;
		    String cabecalho_rodape = null;
			String template = null;
			Map<String, Object> variaveis = new HashMap<>();

		    if (atendimento.isPresent()) {
		    	if (recursoLai.getTipo().equals(TipoRecursoEnum.RECURSO_2_INSTANCIA.getCodigo())) {
				    cabecalho_texto = atendimento.get().getInstituicao().equals(1l) ? "Agradecemos por entrar em contato com a Direção Regional do SENAI Pernambuco." : "Agradecemos por entrar em contato com a Superintendência do SESI Pernambuco.";
				    cabecalho_rodape = atendimento.get().getInstituicao().equals(1l) ? "Direção Regional do SENAI Pernambuco." : "Superintendência do SESI Pernambuco.";
		    	}else {
				    cabecalho_texto = "Agradecemos por entrar em contato com a Ouvidoria.";
				    cabecalho_rodape = "Unidade Corporativa de Ouvidoria do Sistema FIEPE.";
		    	}
				variaveis.put("atendimento", atendimento.get());
				variaveis.put("cabecalho_texto", cabecalho_texto);
				variaveis.put("cabecalho_rodape", cabecalho_rodape);
				template = "mail/emailcadastrorecurso";
				mailer.enviarEmail(Arrays.asList(atendimento.get().getEmail()), template, variaveis, "Serviço de Atendimento ao Cidadão do Sistema FIEPE", "suporte@welssoncavalcante.com.br");

				variaveis = new HashMap<>();

				if (recursoLai.getTipo().equals(TipoRecursoEnum.RECURSO_2_INSTANCIA.getCodigo())) {
					variaveis.put("atendimento", atendimento.get());
					variaveis.put("despacho", recursoLai.getJustificativa());
					variaveis.put("dataPrazoResposta", recursoLai.getDataPrazo());
					variaveis.put("recurso_instancia_1", recursolai_Instancia_1);
					variaveis.put("url", "https://portalouvidoria.com.br:8501/gconweb/resposta-recurso/"+recursoLai.getParametro());
					template = "mail/emailencaminhamentorecurso";
					mailer.enviarEmail(Arrays.asList(atendimento.get().getEmail()), template, variaveis, "Serviço de Atendimento ao Cidadão do Sistema FIEPE", "suporte@welssoncavalcante.com.br");
				}				
				
		    }
		    
		return recursoLaiMapper.toDto(recursoLai);
	}
	

}
