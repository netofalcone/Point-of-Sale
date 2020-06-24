package pos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pos.service.UserService;

 /*Mapeia url, endereços, autoriza ou bloqueia acessos a URL
 
 * */
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	/*configura as solicitações de acesso por http*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
	
		/*ativando a proteção contra usuário que não estão válidados por token*/		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())		
		/* Ativando a permissão para acesso a pagina incial do sistema EX: www.sistema.com.br/		 */		
		.disable().authorizeRequests().antMatchers("/").permitAll()
		 .antMatchers("/index.html").permitAll()

		//permite os clientes em portas diferentes em servidores diferentes, fazerem varias opções de uso da API, leitura, consultas, atualizações, deletes...
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

		 /* URL DE LOGOUT - redireciona após o user deslogar do sistema */
		 .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")		 
		 /*Mapeia a URL de Logout e inválida o usuário*/
		 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
						
		
		/*Filtra as requesições de login para autenticação*/		
		.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)		
		/*Filtra demais requesições para verificar a presença do token http jwt no HEAD http*/
		.addFilterBefore(new JWTApiAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}	
	
		
	
	
	// AuthenticationManagerBuilder é o gerenciador de autenticaçaõ	
	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {			
		/*Serivce que irá consultar o usuário no banco de dados*/
		auth.userDetailsService(userService)
		/*padrão de codificação de senha */
		.passwordEncoder(new BCryptPasswordEncoder());
			
		}
	
	
	
	
	
}


