package pos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pos.model.Permission;
import pos.model.User;

import java.util.List;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {

    @Query(value = "select p.name from role_permission rp inner join permission p on p.id = rp.id_permission where rp.id_role = ?1", nativeQuery = true)
    List<String> findPermissionByRole(Integer idRole);
}
