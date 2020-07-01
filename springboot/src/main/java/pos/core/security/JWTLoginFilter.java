package pos.core.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import pos.core.config.AppContext;
import pos.dto.LoginDTO;
import pos.util.JsonUtil;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private JWTTokenAuthenticationService getTokenService() {
        return AppContext.getApplicationContext().getBean(JWTTokenAuthenticationService.class);
    }

    public JWTLoginFilter(String url) {
        super(new AntPathRequestMatcher(url));
        this.setAuthenticationManager(new JwtAuthenticationManager());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        SecurityUtil.fillAccessControlHeader(response);
        AccountCredentials user = new ObjectMapper().readValue(request.getInputStream(), AccountCredentials.class);

        return new JwtAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
                        user.getPassword(), Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        LoginDTO dto = (LoginDTO) authResult.getPrincipal();
        dto = getTokenService().addAuthentication(response, dto);
        response.getWriter().write(JsonUtil.toJson(dto));
    }
}
