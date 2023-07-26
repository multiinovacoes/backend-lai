package br.com.multiinovacoes.gcon.lai.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.tomcat.util.codec.binary.Base64;


public final class UploadGcon {
	
	private UploadGcon() {}
	
	public static void upload(String base64, String urlAnexo)throws IOException {
		try {
			byte[] anexo2 = Base64.decodeBase64(base64.split(",")[1]);
			OutputStream out = new FileOutputStream(
					"C:\\jboss-4.2.1.GA_CLOUD\\server\\default\\deploy\\multiwork.war\\arquivos\\7\\lai\\"+urlAnexo); 
			out.write(anexo2);
			out.close();
		}catch (Exception e) {
			e.getMessage();
		}
			
	}


}
