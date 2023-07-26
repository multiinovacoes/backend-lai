package br.com.multiinovacoes.gcon.lai.controller;

import static br.com.multiinovacoes.gcon.lai.controller.RecursoLaiController.RECURSO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.lai.model.mapper.RecursoLaiMapper;
import br.com.multiinovacoes.gcon.lai.model.request.RecursoLaiRequest;
import br.com.multiinovacoes.gcon.lai.model.response.ListaRecursoLaiResponse;
import br.com.multiinovacoes.gcon.lai.model.response.RecursoLaiResponse;
import br.com.multiinovacoes.gcon.lai.repository.RecursoLaiRepository;
import br.com.multiinovacoes.gcon.lai.security.GconSecurity;
import br.com.multiinovacoes.gcon.lai.service.RecursoLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Recurso Lai", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = RECURSO	)
public class RecursoLaiController {
	
	public static final String RECURSO = "/recurso";
	public static final String CONSULTA_PEDIDO = "/consultaPedido";
	public static final String PARAMETRO = "/{codigo}";
	public static final String AUTORIZA_RECURSO = "/autoriza-recurso/{codigoPedido}";

	
	@Autowired 
	RecursoLaiRepository recursoLaiRepository;
	
	@Autowired
	RecursoLaiService recursoLaiService;
	
	@Autowired
	RecursoLaiMapper recursoLaiMapper;
	
	@Autowired
	GconSecurity gconSecurity;


	@ApiOperation(value = "Cadastrar uma manifestação")
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public RecursoLaiResponse  salvarRecurso(@Valid @RequestBody RecursoLaiRequest request) throws Exception{
		return new RecursoLaiResponse(recursoLaiService.salvarRecursoLai(request));
	}
	
	
	@ApiOperation(value = "Lista de recursos", nickname = CONSULTA_PEDIDO)
	@GetMapping(path = CONSULTA_PEDIDO, produces = APPLICATION_JSON_VALUE)
	public ListaRecursoLaiResponse getListaRecursos(Long idPedido) {
		return new ListaRecursoLaiResponse(recursoLaiMapper.fromResponseList(recursoLaiRepository.findByAtendimento(idPedido)));
	}

	@ApiOperation(value = "Obter um recurso para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public RecursoLaiResponse getEditar(@PathVariable Long codigo) {
		return new RecursoLaiResponse(recursoLaiService.getRecursoLai(codigo));
	}
	
	@ApiOperation(value = "Obter um recurso para edição", nickname = AUTORIZA_RECURSO)
	@GetMapping(path = AUTORIZA_RECURSO, produces = APPLICATION_JSON_VALUE)
	public RecursoLaiResponse getAutorizaRecurso(@PathVariable String codigoPedido) {
		return new RecursoLaiResponse(recursoLaiService.getAutorizaRecurso(Long.parseLong(codigoPedido)));
	}


}
