package br.com.multiinovacoes.gcon.lai.mail;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@Async
	public void enviarEmail(List<String> destinatarios, String template, Map<String, Object> variaveis, String siglaOrgao, String emailSistema) {
		try {
			Context context = new Context(new Locale("pt", "BR"));
			variaveis.entrySet().forEach(e -> context.setVariable(e.getKey(), e.getValue()));
			String mensagem = thymeleaf.process(template, context);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailSistema, siglaOrgao);
			helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
			helper.setSubject("Cadastro de Atendimento");
			helper.setText(mensagem, true);
			mailSender.send(mimeMessage);
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException("Problemas com o envio de e-mail!", e); 
		}
	}
}