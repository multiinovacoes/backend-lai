package br.com.multiinovacoes.gcon.lai.controller;

import static br.com.multiinovacoes.gcon.lai.controller.AtendimentoController.ATENDIMENTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.multiinovacoes.gcon.lai.enums.StatusAtendimentoEnum;
import br.com.multiinovacoes.gcon.lai.mail.Mailer;
import br.com.multiinovacoes.gcon.lai.model.Pedido;
import br.com.multiinovacoes.gcon.lai.model.mapper.AtendimentoMapper;
import br.com.multiinovacoes.gcon.lai.model.response.AtendimentoResponse;
import br.com.multiinovacoes.gcon.lai.model.response.ListaAtendimentoResponse;
import br.com.multiinovacoes.gcon.lai.repository.AtendimentoRepository;
import br.com.multiinovacoes.gcon.lai.security.GconSecurity;
import br.com.multiinovacoes.gcon.lai.service.AtendimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Atendimento", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ATENDIMENTO	)
public class AtendimentoController {
	
	public static final String ATENDIMENTO = "/atendimentos";
	public static final String LOGO_TIPO = "/logotipo";
	public static final String TIPO_MANIFESTANTE = "/tipoManifestantes";
	public static final String NATUREZAS = "/naturezas";
	public static final String ASSUNTOS = "/assuntos";
	public static final String BAIRROS = "/bairros";
	public static final String TIPO_DOCUMENTOS = "/tipoDocumentos";
	public static final String CONSULTA_TIPO_DOCUMENTO = "/consulta-tipodocumento";
	public static final String CONSULTA = "/consulta";
	public static final String CONSULTA_ATENDIMENTO = "/consultaAtendimento";
	public static final String CONSULTA_ATENDIMENTO_CONCLUIDO = "/consulta-atendimento-concluido";
	public static final String PESQUISAR_PEDIDO = "/pesquisar-pedido";
	public static final String CONSULTA_RESPOSTA_PARCIAL = "/consultaRespostaParcial";
	public static final String PESQUISA_SATISFACAO = "/pesquisaSatisfacao";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired 
	AtendimentoRepository atendimentoRepository;
	
	@Autowired
	AtendimentoService atendimentoService;
	
	@Autowired
	AtendimentoMapper atendimentoMapper;

	@Autowired
	private Mailer mailer;
	
	@Autowired
	GconSecurity gconSecurity;


	@ApiOperation(value = "Cadastrar uma manifestação")
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public AtendimentoResponse  salvarAtendimento(@Valid @RequestBody Pedido request) throws Exception{
		return new AtendimentoResponse(atendimentoService.salvarAtendimento(request));
	}
	
	
	@ApiOperation(value = "Lista de novos atendimentos", nickname = CONSULTA_ATENDIMENTO)
	@GetMapping(path = CONSULTA_ATENDIMENTO, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getListaNovosAtendimentos() {
		return new ListaAtendimentoResponse(atendimentoRepository.findByUsuarioCriacao(gconSecurity.getIdUsuario()));
	}

	@ApiOperation(value = "Lista de novos atendimentos", nickname = CONSULTA_ATENDIMENTO_CONCLUIDO)
	@GetMapping(path = CONSULTA_ATENDIMENTO_CONCLUIDO, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getListaAtendimentosConcluidos() {
		return new ListaAtendimentoResponse(atendimentoRepository.findByUsuarioCriacaoAndStatusAtendimento(gconSecurity.getIdUsuario(), StatusAtendimentoEnum.STATUS_RESOLVIDO.getCodigo()));
	}

	@ApiOperation(value = "Lista de novos atendimentos", nickname = PESQUISAR_PEDIDO)
	@GetMapping(path = PESQUISAR_PEDIDO, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getPesquisaPedido(String numeroProtocolo, String descricao) {
		return new ListaAtendimentoResponse(atendimentoService.getListaPedidos(numeroProtocolo, descricao));
	}

	
	
	@ApiOperation(value = "Obter um atendimento para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AtendimentoResponse getEditarArea(@PathVariable Long codigo) {
		return new AtendimentoResponse(atendimentoService.getAtendimento(codigo));
	}
	
	@PostMapping("/anexo")
	public String uploadAnexo(@RequestParam MultipartFile[] anexo) throws IOException {
		for (int i = 0; i < anexo.length; i++) {
			OutputStream out = new FileOutputStream(
					"C:\\jboss-4.2.1.GA_UBEC\\server\\default\\deploy\\multiwork.war\\arquivos\\" + anexo[i].getOriginalFilename());
			out.write(anexo[i].getBytes());
			out.close();
		}
		return "ok";
	}
	
	@GetMapping(path = "ajusteProtocolo", produces = APPLICATION_JSON_VALUE)
	public Integer ajusteNumeroProtocoloCorreto(Long orgao) {
		//return atendimentoService.ajuste(orgao);
		return 0;
	}
	
	
	@GetMapping(path = "email", produces = APPLICATION_JSON_VALUE)
	public void enviarEmail() {
	    Map<String, Object> variaveis = new HashMap<>();
	    variaveis.put("sigla", "TESTE");
		String template = "mail/emailouvidoria";
		mailer.enviarEmail(Arrays.asList("welssoncavalcante@gmail.com"), template, variaveis, "TESTE", "multi@multiinovacoes.com.br");
	}


}
