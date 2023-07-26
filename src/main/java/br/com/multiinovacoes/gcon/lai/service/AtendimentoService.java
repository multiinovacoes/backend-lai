package br.com.multiinovacoes.gcon.lai.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.lai.enums.StatusAtendimentoEnum;
import br.com.multiinovacoes.gcon.lai.enums.StatusEnum;
import br.com.multiinovacoes.gcon.lai.mail.Mailer;
import br.com.multiinovacoes.gcon.lai.model.Atendimento;
import br.com.multiinovacoes.gcon.lai.model.Feriado;
import br.com.multiinovacoes.gcon.lai.model.Manifestante;
import br.com.multiinovacoes.gcon.lai.model.ManifestanteAtendimento;
import br.com.multiinovacoes.gcon.lai.model.Orgao;
import br.com.multiinovacoes.gcon.lai.model.Pedido;
import br.com.multiinovacoes.gcon.lai.model.dto.AtendimentoDto;
import br.com.multiinovacoes.gcon.lai.model.dto.RecursoLaiDto;
import br.com.multiinovacoes.gcon.lai.model.mapper.AtendimentoMapper;
import br.com.multiinovacoes.gcon.lai.model.mapper.RecursoLaiMapper;
import br.com.multiinovacoes.gcon.lai.repository.AtendimentoRepository;
import br.com.multiinovacoes.gcon.lai.repository.FeriadoRepository;
import br.com.multiinovacoes.gcon.lai.repository.ManifestanteAtendimentoRepository;
import br.com.multiinovacoes.gcon.lai.repository.OrgaoRepository;
import br.com.multiinovacoes.gcon.lai.repository.RecursoLaiRepository;
import br.com.multiinovacoes.gcon.lai.security.GconSecurity;



@Service
@Transactional
public class AtendimentoService {
	
	
	@Autowired
	private AtendimentoMapper atendimentoMapper;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private Mailer mailer;

	@Autowired
	private OrgaoRepository orgaoRepository;
	
	@Autowired
	private FeriadoRepository feriadoRepository;
	
	@Autowired
	private ManifestanteService manifestanteService;
	
	@Autowired
	private ManifestanteAtendimentoRepository manifestanteAtendimentoRepository;
	
	@Autowired
	GconSecurity gconSecurity;
	
	@Autowired
	private RecursoLaiRepository recursoLaiRepository;
	
	@Autowired
	private RecursoLaiMapper recursoLaiMapper;
	
	public LocalDate adicionarDiasUteis(Integer qtdeDiasAcrescentados, Long codigoOrgao) {
		List<Feriado> feriadoInfoList = null;
		LocalDate data = LocalDate.now();
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
	
	
	public AtendimentoDto getAtendimento(Long codigoAtendimento) {
		Atendimento atendimento = atendimentoRepository.getById(codigoAtendimento);
		List<RecursoLaiDto> lista = recursoLaiMapper.fromResponseList(recursoLaiRepository.findByAtendimento(codigoAtendimento));
		AtendimentoDto atendimentoDto = atendimentoMapper.toDto(atendimento); 
		if (!lista.isEmpty()) {
			atendimentoDto.setListaRecursos(lista);
		}
		return atendimentoDto; 
	}

	
	public List<Atendimento> getListaPedidos(String numeroProtocolo, String descricao) {
		if (numeroProtocolo != null && descricao != null) {
			return atendimentoRepository.findByNumeroDescricao(numeroProtocolo, descricao);
		}
		else if (numeroProtocolo != null && descricao == null) {
			return atendimentoRepository.findByNumeroProtocolo(numeroProtocolo);
		}else if (numeroProtocolo == null && descricao != null) {
			return atendimentoRepository.findByDescricao(descricao);
		}
		return null;
	}

	public AtendimentoDto salvarAtendimento(Pedido pedido) throws IOException{
			//Configuracao conf = configuracaoRepository.findByOrgao(7l);
		    Atendimento atendimento = new Atendimento();
		    Orgao orgao = orgaoRepository.getById(7l);
		    
		    Manifestante manifestante = manifestanteService.getManifestante(gconSecurity.getIdUsuario());
		    
		    atendimento.setArea(pedido.getArea());
		    atendimento.setInstituicao(pedido.getInstituicao());
		    atendimento.setEmail(manifestante.getEmail());
            atendimento.setNatureza(8l);
		    atendimento.setStatus(StatusEnum.ATIVO.getCodigo());
		    atendimento.setStatusAtendimento(StatusAtendimentoEnum.STATUS_ANDAMENTO.getCodigo());
		    atendimento.setDataPrazo(adicionarDiasUteis(20, orgao.getId()));
		    atendimento.setDescricao(pedido.getDescricao());
		    atendimento.setAssunto(0l);
		    atendimento.setNomeSolicitante(gconSecurity.getAuthentication().getName());
		    atendimento.setObservacao("");
		    atendimento.setSatisfaz(0);
		    atendimento.setResposta("");
		    atendimento.setOrgao(orgao.getId()); 
		    atendimento.setAnoAtendimento(LocalDate.now().getYear());
		    atendimento.setUsuarioCriacao(gconSecurity.getIdUsuario());
		    atendimento.setDataCriacao(LocalDateTime.now()); 
		    atendimento.setUsuarioAlteracao(gconSecurity.getIdUsuario());
		    atendimento.setDataAlteracao(LocalDateTime.now());
		    atendimento.setOrigemContato(1l);
		    atendimento.setTipoDocumento(manifestante.getCodigoDocumento());
		    atendimento.setNumeroDocumento(manifestante.getNumeroDocumento());
		    atendimento.setCidade(manifestante.getCidade());
		    atendimento.setUf(manifestante.getUf());
		    atendimento.setTipoUsuario(Integer.parseInt(manifestante.getTipo()));
		    atendimento.setTelefone(manifestante.getTelefone());
		    
		    atendimento.setDataEntrada(LocalDate.now());
		    atendimento.setNumeroAtendimento(atendimentoRepository.getMaxNumeroAtendimento(LocalDate.now().getYear(), orgao.getId())+1);
		    atendimento.setNumeroProtocolo(atendimento.getAnoAtendimento() +""+atendimento.getNumeroAtendimento());
		    atendimento.setSequencialOrgao(atendimentoRepository.getMaxSequencialOrgao(LocalDate.now().getYear(), orgao.getId())+1);
		    atendimentoRepository.save(atendimento);
		    
		    
		    ManifestanteAtendimento manifestanteAtendimento = new ManifestanteAtendimento();
		    manifestanteAtendimento.setId(manifestanteAtendimentoRepository.getMaxSequencial());
		    manifestanteAtendimento.setIdAtendimento(atendimento.getId());
		    manifestanteAtendimento.setIdManifestante(gconSecurity.getIdUsuario());
		    manifestanteAtendimentoRepository.save(manifestanteAtendimento);
		    
		    
//		    if (atendimentoDto.getListaAnexoDto() != null) {
//			    for (ListaAnexoDto anexos : atendimentoDto.getListaAnexoDto()) {
//			    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//			    	String nomeArq = timeStamp + "_" + anexos.getNomeArquivo();
//		    		buildFile(anexos.getStringBase64(), nomeArq);
//			    	Anexo anexo = new Anexo();
//			    	anexo.setAtendimento(atendimento.getId());
//			    	anexo.setDataAnexo(LocalDate.now());
//			    	anexo.setNomeArquivo(nomeArq);
//			    	anexo.setNumeroAtendimento(atendimento.getNumeroAtendimento());
//			    	anexo.setOrgao(atendimento.getOrgao());
//			    	anexo.setOrigem(0);
//			    	anexo.setVisivel(1);
//			    	anexo.setId(anexoRepository.getMaxSequencialId()+1);
//			    	anexoRepository.save(anexo);
//			    }
//		    }
			String template = null;
		    Map<String, Object> variaveis = new HashMap<>();
		    variaveis.put("sigla", orgao.getSigla());
			variaveis.put("atendimento", atendimento);
			variaveis.put("url", atendimento.getOrgao());
			template = "mail/emailcadastromanifestacao";
			mailer.enviarEmail(Arrays.asList(atendimento.getEmail()), template, variaveis, "Serviço de Atendimento ao Cidadão do Sistema FIEPE", "suporte@welssoncavalcante.com.br");
//		if (atendimentoDto.getNatureza() == 5) {
//			variaveis.put("atendimento", atendimento);
//			variaveis.put("texto", atendimento.getDescricaoOque());
//			//variaveis.put("logo", logo);			
//			template = "mail/emailouvidoriadenuncia";
//			mailer.enviarEmail(Arrays.asList("geraldo.silva@ubec.edu.br","flavio.azevedo@lasalle.org.br","rosilandia.oliveira@ubec.edu.br"), template, variaveis, orgao.getSigla(), "suporte@welssoncavalcante.com.br");
//		}else {
//			template = "mail/emailouvidoria";
//			mailer.enviarEmail(Arrays.asList(orgao.getEmailOrgao()), template, variaveis, orgao.getSigla(), "suporte@welssoncavalcante.com.br");
//			if (orgao.getSigla().equals("UCB") || orgao.getSigla().equals("UNILESTE") || orgao.getSigla().equals("FICR") || orgao.getSigla().equals("UNICATOLICA")) {
//				mailer.enviarEmail(Arrays.asList("ouvidoria@ubec.edu.br"), template, variaveis, orgao.getSigla(), "suporte@welssoncavalcante.com.br");
//			}
//		}
		return atendimentoMapper.toDto(atendimento);
	}
	
	
	
	
	
	private void buildFile(String base64, String urlAnexo)throws IOException {
		try {
			byte[] anexo2 = Base64.decodeBase64(base64);
			OutputStream out = new FileOutputStream(
					"C:\\gcon_arquivos\\arquivos\\"+urlAnexo);
			out.write(anexo2);
			out.close();
		}catch (Exception e) {
			e.getMessage();
		}
			
	}
	
	
	private void buildFileUBEC(String base64, String urlAnexo, Long codigoOrgao)throws IOException {
		try {
			byte[] anexo2 = Base64.decodeBase64(base64.split(",")[1]);
			OutputStream out = new FileOutputStream(
					"C:\\jboss-4.2.1.GA_UBEC\\server\\default\\deploy\\multiwork.war\\arquivos\\"+codigoOrgao.toString()+"\\"+urlAnexo);
			out.write(anexo2);
			out.close();
		}catch (Exception e) {
			e.getMessage();
		}
			
	}
	
	
//	public Integer ajuste(Long codigoOrgao) {
//		List<Atendimento> lista = atendimentoRepository.findByOrgao(codigoOrgao);
//		int contador = 0;
//		for (Atendimento atendimento : lista) {
//			if (!atendimento.getEmail().equals("")) {
//				Map<String, Object> variaveis = new HashMap<>();
//				variaveis.put("atendimento", atendimento);
//				variaveis.put("url", atendimento.getOrgao());
//				variaveis.put("logo", "");
//				String template = "mail/emailcadastromanifestacao";
//				mailer.enviarEmail(Arrays.asList(atendimento.getEmail()), template, variaveis, "UCB");
//				contador = contador + 1;
//			}
//		}
//		return contador;
//	}



}
