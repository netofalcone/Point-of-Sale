package pos.dto;

import java.util.ArrayList;
import java.util.List;

public class LoginDTO {

    private Integer id;

    private String email;

    private Integer idRole;

    private String name;

    private String token;

    private List<String> permissions;

    public LoginDTO() {
    }

    public LoginDTO(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public LoginDTO(Integer id, String email, Integer idRole) {
        this.id = id;
        this.email = email;
        this.idRole = idRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
