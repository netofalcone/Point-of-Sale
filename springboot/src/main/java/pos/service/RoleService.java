package pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.dto.RoleDTO;
import pos.model.Role;
import pos.repository.RoleRepository;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleRepository getRepository() {
        return this.roleRepository;
    }

    public Role getRoleById(Integer id) {
        return getRepository().findRoleById(id);
    }

    public RoleDTO toRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());
        roleDTO.setPermissions(role.getPermissions());
        return roleDTO;
    }
}
