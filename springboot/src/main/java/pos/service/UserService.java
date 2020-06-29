package pos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import pos.model.User;
import pos.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
		
		public List<User> listUsers() {
				List<User> users =  (List<User>) userRepository.findAll();		
				return users;
 		}
		
		
		public User userEmail(@PathVariable (value = "emailUser") String email) {			
				return userRepository.findUserbyEmail(email);
	 	}
	
		
		
		public Optional<User> userWithId(@PathVariable (value = "idUser") Long id) {			
			   Optional<User> user =  userRepository.findById(id);		
				return user;
	 	}
		
		public User storeUser( @RequestBody User user) {

			for (int phone = 0; phone < user.getPhones().size(); phone++) {
				user.getPhones().get(phone).setUser(user);
			}

			String passwordCrypted = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(passwordCrypted);
			return userRepository.save(user);
		}
		
		public User updateUser( @RequestBody User user) {
			for (int phone = 0; phone < user.getPhones().size(); phone++) {
				user.getPhones().get(phone).setUser(user);
			}

			/*Se as senhas forem diferentes, encripta a nova e manda atualizar */
			User userVariableTemporary = userRepository.findUserbyEmail(user.getEmail());
			if (!userVariableTemporary.getPassword().equals(user.getPassword())) {

				String passwordCrypted = new BCryptPasswordEncoder().encode(user.getPassword());
				user.setPassword(passwordCrypted);
			}

			return userRepository.save(user);
		}
		
		public void deleteUser( @PathVariable (value = "idUser") Long id 
				) {
			 userRepository.deleteById(id);
			return ;			
		}
		
		
		//consultar usuario no banco
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
						
			User user = userRepository.findUserbyEmail(username);
			
			if (user == null) {
				throw new UsernameNotFoundException("Usuário não encontrado");
			}
			// caso esteja correta, já retorna email, password e autorizações
			// poderia retonar, apenas o user, mas retornando um new user, ele já retorna com alguns padrões 
			// do propio framework com validações
			return new org.springframework.security.core.userdetails.User(
					user.getEmail(),
					user.getPassword(),
					user.getAuthorities());
			//
			}


		

}
