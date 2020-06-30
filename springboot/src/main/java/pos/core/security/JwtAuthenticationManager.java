package pos.core.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import pos.core.config.AppContext;
import pos.dto.LoginDTO;
import pos.model.User;
import pos.service.PermissionService;
import pos.service.UserService;

import java.util.ArrayList;

public class JwtAuthenticationManager implements AuthenticationManager {

    private UserService userService;

    private PermissionService permissionService;

    public PermissionService getPermissionService() {
        if(this.permissionService == null) {
            this.permissionService = AppContext.getApplicationContext().getBean(PermissionService.class);
        }
        return this.permissionService;
    }

    public UserService getUserService() {
        if(this.userService == null) {
            this.userService = AppContext.getApplicationContext().getBean(UserService.class);
        }
        return this.userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = this.getUserService().findByEmailAndPassword(authentication.getName(), (String) authentication.getCredentials());
        if(user != null) {
            return new UsernamePasswordAuthenticationToken(toLoginDTO(user), authentication.getCredentials());
        }
        throw new BadCredentialsException("User or password are invalid.");
    }

    private LoginDTO toLoginDTO (User user) {
        LoginDTO login = new LoginDTO();
        login.setId(user.getId());
        login.setName(user.getName());
        login.setIdRole(user.getRole().getId());
        login.setEmail(user.getEmail());
        this.addPermissions(login, user.getRole().getId());
        return login;
    }

    private LoginDTO addPermissions(LoginDTO dto, Integer idRole) {
        dto.setPermissions(new ArrayList<>(getPermissionService().getPermissions(idRole)));
        return dto;
    }
}
