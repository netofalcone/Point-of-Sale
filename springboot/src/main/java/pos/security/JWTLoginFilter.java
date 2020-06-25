package pos.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import pos.model.User;


/*Estabelece o gerenciado de Token*/
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    /*configurando o gerenciador de autenticação*/
    protected JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        /*obriga a autenticar a URL*/
        super(new AntPathRequestMatcher(url));
        /*Gerenciador de autenticação */
        setAuthenticationManager(authenticationManager);

    }

    /*retona usuario ao processar a autenticação*/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // Está pegando o token para validar
        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        /*Retorna o usuario login, senha e acessos*/
        return getAuthenticationManager()
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(), user.getPassword()));
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        new JWTTokenAuthenticationService().addAuthentication(response, authResult.getName());
    }


}
