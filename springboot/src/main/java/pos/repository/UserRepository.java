package pos.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pos.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findUserById(Integer id);

    User findByEmailAndPassword(String email, String password);

    User findByCpf(String cpf);
}
