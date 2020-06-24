package pos.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pos.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	
	@Query("select user from User user where user.email = ?1")
	User findUserbyEmail(String email);


}
