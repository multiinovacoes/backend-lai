package br.com.multiinovacoes.gcon.lai.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.lai.mail.Mailer;
import br.com.multiinovacoes.gcon.lai.model.Manifestante;
import br.com.multiinovacoes.gcon.lai.model.dto.ManifestanteDto;
import br.com.multiinovacoes.gcon.lai.model.mapper.ManifestanteMapper;
import br.com.multiinovacoes.gcon.lai.repository.ManifestanteRepository;
import br.com.multiinovacoes.gcon.lai.util.CriptografiaSenha;

@Service
@Transactional
public class ManifestanteService {
	
	@Autowired
	private ManifestanteRepository manifestanteRepository;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	private ManifestanteMapper manifestanteMapper;
	
	public ManifestanteDto salvar(ManifestanteDto manifestanteDto) {

		manifestanteDto.setNumeroDocumento(manifestanteDto.getNumeroDocumento().replaceAll("[^\\d ]", ""));
		manifestanteDto.setSenha(CriptografiaSenha.getSenhaCodificada(manifestanteDto.getSenha()));
		manifestanteDto.setId(manifestanteRepository.getMaxId()+1);
		Manifestante manifestante = manifestanteMapper.toManifestante(manifestanteDto);
		manifestanteRepository.save(manifestante);

		String template = null;
	    Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("usuario", manifestante.getNumeroDocumento());
		variaveis.put("senha", "******");
		template = "mail/emailcadastromanifestante";
		mailer.enviarEmail(Arrays.asList(manifestante.getEmail()), template, variaveis, "", "suporte@welssoncavalcante.com.br");
		
		return manifestanteMapper.toDto(manifestante);
	}

	public ManifestanteDto atualizar(ManifestanteDto manifestanteDto) {
		Manifestante manifestante = manifestanteMapper.toManifestante(manifestanteDto);
		manifestanteRepository.save(manifestante);
		return manifestanteMapper.toDto(manifestante);
	}

	public Manifestante getManifestante(Long codigoManifestante) {
		return manifestanteRepository.getManifestante(codigoManifestante);
	}
	

}
