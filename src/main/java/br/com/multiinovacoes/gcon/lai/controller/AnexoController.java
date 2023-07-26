package br.com.multiinovacoes.gcon.lai.controller;

import static br.com.multiinovacoes.gcon.lai.controller.AnexoController.ANEXO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.lai.model.mapper.AnexoMapper;
import br.com.multiinovacoes.gcon.lai.model.request.ListaAnexoRequest;
import br.com.multiinovacoes.gcon.lai.model.response.ListaAnexoResponse;
import br.com.multiinovacoes.gcon.lai.service.AnexoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Anexo", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ANEXO	)
public class AnexoController {
	
	public static final String ANEXO = "/anexos";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";
	
	
	@Autowired
	AnexoService anexoService;
	
	@Autowired
	AnexoMapper anexoMapper;
	
	@ApiOperation(value = "Obter lista de anexos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaAnexoResponse getListaAnexos(Long codigoAtendimento) {
		return new ListaAnexoResponse(anexoMapper.fromResponseList(anexoService.getListaAnexos(codigoAtendimento)));
	}
	
	@ApiOperation(value = "Salva um arquivo")
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public void salvarAnexo(@Valid @RequestBody ListaAnexoRequest request) {
		anexoService.salvarArquivo(request);
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma despacho", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirAnexo(@PathVariable Long codigo) {
		anexoService.excluir(codigo);
	}
	
	


}
