package pos.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
		response.addHeader("Access-Control-Allow-Origin", "*");
		/*escreve  token como resposta no corpo do http*/
		response.getWriter().write("{\"Authorization\": \""+token+"\"}");
	
	}
	
	
	
	
	
	/* Recebendo a requisição do navegador, ele válida o token que está no navegador. 
	  Retorna o usuário Validado com token ou caso não seja válido, retorna null*/	

	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		/*Pega o token enviado no HEADER http*/
		String token = request.getHeader(HEADER_STRING);		
		if(token != null) {
			/*faz a validação do token do usuario na requisição */			 
			 String user = Jwts.parser().setSigningKey(SECRET) /* Bearer jsdfsifhwioahwaiorfhwieo*/
					 .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))/* jsdfsifhwioahwaiorfhwieo*/
					 .getBody().getSubject(); /* retorna o usuario*/
			
			 if (user != null) {	
				 // buscando no canco pra ver se existe o user
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
			 				
			response.addHeader("Access-Control-Allow-Origin", "*");
			return null; /*Não autorizado*/
		}
	
	
	
	
	
	
	
	}
