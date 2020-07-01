package pos.repository;

import org.springframework.data.repository.CrudRepository;
import pos.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findRoleById(Integer id);
}
