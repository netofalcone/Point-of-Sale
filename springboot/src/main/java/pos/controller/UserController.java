package pos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pos.model.User;
import pos.repository.UserRepository;
import pos.service.UserService;






@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;


	@GetMapping(value = "/", produces = "Application/json")
	@CacheEvict(value="cacheUserEvict", allEntries= true ) // Remove do cache coisas que não estão sendo utilizadas
	@CachePut(value="cacheUserPut") // Indentifica atualizações no banco de dados, e atualiza o cache atual.
	public ResponseEntity <List<User>>  users() throws InterruptedException {			
		List<User> users =  userService.ListUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
 	 } 
	


	@GetMapping(value = "/{idUser}", produces = "Application/json")
	@CacheEvict(value="cacheUserEvict", allEntries= true ) 
	@CachePut(value="cacheUserPut") 
	public ResponseEntity <User> index(@PathVariable (value = "idUser") Long id) {
		Optional<User> user = userService.userWithId(id);
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	 }
			

	
	@PostMapping(value = "/", produces = "Application/json")
	public ResponseEntity <User> store( @RequestBody User user) {
		User userSalved = userService.storeUser(user);
		return new ResponseEntity<User>(userSalved, HttpStatus.CREATED);
	 
	 }
	
	
	
	
	@PutMapping(value = "/", produces = "Application/json")
	public ResponseEntity <User> update( @RequestBody User user) {
		User userUpdate = userService.updateUser(user);	
		return new ResponseEntity<User>(userUpdate, HttpStatus.OK);
	
	}

	
	
	@DeleteMapping(value = "/{idUser}", produces = "Application/text")
	public String delete ( @PathVariable (value = "idUser") Long id) {								
		userService.deleteUser(id);
		return "User Deleted";
	}


}
