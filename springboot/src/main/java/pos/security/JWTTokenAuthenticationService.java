package pos.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.*;
import org.springframework.beans.BeansException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pos.ApplicationContextLoad;
import pos.model.User;
import pos.repository.UserRepository;

@Service
@Component
public class JWTTokenAuthenticationService {
		
	
	/*Tempo de validade do token, 2 DIAS*/
	private static final long EXPIRATION_TIME = 172800000;
	/*SENHA UNICA PARA COMPOR A AUTENTICAÇÃO*/
	private static final String SECRET = "SenhaForte";	
	/* Prefixo padrão de Token*/
	private static final String TOKEN_PREFIX = "Bearer";	
	/* Prefixo que será retornado para o cabeçalho da resposta*/
	private static final String HEADER_STRING = "Authorization";	
	
	/*Gerando token de autenticação e adicionando ao cabeçalho e resposta Http
	 * é invocado depois que faz a autenticação a primeria vez*/
	public void addAuthentication(HttpServletResponse response, String username) throws IOException {		
			
		/*Montagem do token*/
		String JWT = Jwts.builder() /*chama o "gerador" de token*/
				.setSubject(username) /*adiciona o usuario*/
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /*tempo de expiração*/
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /*compactação e algoritimo de geração de senha*/
	
		/*Junta o token com o prefixo*/
		String token = TOKEN_PREFIX + " " + JWT;				
		/*adiciona no cabeçalho http*/		
		response.addHeader(HEADER_STRING, token);		
		openCors(response);
		/*escreve  token como resposta no corpo do http*/
		response.getWriter().write("{\"Authorization\": \""+token+"\"}");
	
	}
	
	
	
	
	
	/* Recebendo a requisição do navegador, esse método pegar o Header da requisição
	 e válida o token que está sendo passado na requisição, depois
	  Retorna o usuário Validado com token ou caso não seja válido, retorna null*/
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		/*Pega o token enviado no HEADER http*/
		String token = request.getHeader(HEADER_STRING);

 	try {

		if(token != null) {
			/*Limpando o token, tirando o Bearer e deixando só o a criptografia*/
			String tokenSkoon = token.replace(TOKEN_PREFIX, "");

			/*extrai o usuario e faz a validação do usuario na requisição */
			 String user = Jwts.parser().setSigningKey(SECRET) /* Bearer jsdfsifhwioahwaiorfhwieo | (Define a chave de assinatura usada para verificar qualquer assinatura digital JWS descoberta. )*/
					 .parseClaimsJws(tokenSkoon)/* reinvidicando o token e decodificando para extrair as informações*/
					 .getBody().getSubject(); /* retorna o usuario*/

			// buscando o user, retornando ele, se exister no banco.
			if (user != null) {
				 User userVariable = ApplicationContextLoad.getApplicationContext()
						 .getBean(UserRepository.class).findUserbyEmail(user);
				 
				 if (userVariable != null) {
					 return new UsernamePasswordAuthenticationToken(
							 userVariable.getEmail(), 
							 userVariable.getPassword(),
							 userVariable.getAuthorities()
							 );
					 }
				 
			 	}
			 }
			 				
			openCors(response);
			return null; /*Não autorizado*/
		}





	private void openCors(HttpServletResponse response) {
		if(response.getHeader("Access-Control-Allow-Origin") == null){
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		if(response.getHeader("Access-Control-Allow-Headers") == null){
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		if(response.getHeader("Access-Control-Request-Headers") == null){
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		if(response.getHeader("Access-Control-Allow-Methods") == null){
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
		
	}
	
	
	
	
	
	
	
	}


}
