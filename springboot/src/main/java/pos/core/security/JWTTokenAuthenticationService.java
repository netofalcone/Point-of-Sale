package pos.core.security;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pos.dto.LoginDTO;

import static java.util.Collections.emptyList;

@Service
public class JWTTokenAuthenticationService {

    private static final long EXPIRATION_TIME = 172800000;
    private static final String SECRET = "SenhaForte";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public LoginDTO addAuthentication(HttpServletResponse response, LoginDTO dto) throws IOException {
        String jwt = Jwts.builder()
                .setId(String.valueOf(dto.getId()))
                .setSubject(dto.getEmail())
                .claim("role", dto.getIdRole())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        dto.setToken(jwt);
        return dto;
    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim())
                    .getBody();

            if (claims != null) {
                return new UsernamePasswordAuthenticationToken(new LoginDTO(Integer.valueOf(claims.getId()),
                        claims.getSubject(), Integer.valueOf(claims.get("role").toString())), null, emptyList()
                );
            }
        }
        return null;
    }

    public Authentication getRefreshAuthentication(HttpServletRequest request) {
        String refreshToken;

        return null;
    }
}