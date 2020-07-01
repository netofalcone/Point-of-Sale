package pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.repository.PermissionRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermissionService {

    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository userRepository) {
        this.permissionRepository = userRepository;
    }

    public PermissionRepository getRepository() {
        return this.permissionRepository;
    }

    public Set<String> getPermissions(Integer idRole) {
        return new HashSet<>(this.getRepository().findPermissionByRole(idRole));
    }

}
