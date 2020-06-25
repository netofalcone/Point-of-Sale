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

@CrossOrigin(origins = "")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/", produces = "Application/json")
    @CacheEvict(value = "cacheUserEvict", allEntries = true)
    @CachePut(value = "cacheUserPut")
    public ResponseEntity<List<User>> users() throws InterruptedException {
        List<User> users = userService.users();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // EXEMPLO DE VERSIONAMENTO DE API, PODE TAMBÉM COLOCAR DIRETO NO REQUESTMAPPING
    @GetMapping(value = "v1/{idUser}", produces = "Application/json")
    @CacheEvict(value = "cacheUserEvict", allEntries = true)
    @CachePut(value = "cacheUserPut")
    public ResponseEntity<User> indexV1(@PathVariable(value = "idUser") Long id) {
        System.out.println(" CHAMANDO VERSÃO 1 de buscar user por id.");
        Optional<User> user = userService.userWithId(id);
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    @GetMapping(value = "v2/{idUser}", produces = "Application/json")
    public ResponseEntity<User> indexV2(@PathVariable(value = "idUser") Long id) {
        System.out.println(" CHAMANDO VERSÃO 2  de buscar user por id.");
        Optional<User> user = userService.userWithId(id);
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "Application/json")
    public ResponseEntity<User> store(@RequestBody User user) {
        for (int phone = 0; phone < user.getPhones().size(); phone++) {
            user.getPhones().get(phone).setUser(user);
        }

        String passwordCrypted = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordCrypted);

        System.out.println("senha foi criptografada: ");

        User userSalved = userService.storeUser(user);
        return new ResponseEntity<User>(userSalved, HttpStatus.CREATED);

    }

    @PutMapping(value = "/", produces = "Application/json")
    public ResponseEntity<User> update(@RequestBody User user) {

        for (int phone = 0; phone < user.getPhones().size(); phone++) {
            user.getPhones().get(phone).setUser(user);
        }

        /*Se as senhas diferentes forem diferentes, encripta a nova e manda atualizar*/
        User userTemporary = userRepository.findUserbyEmail(user.getEmail());
        if (!userTemporary.getPassword().equals(user.getPassword())) {

            String passwordCrypted = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(passwordCrypted);
        }

        User userUpdate = userService.updateUser(user);
        return new ResponseEntity<User>(userUpdate, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{idUser}", produces = "Application/text")
    public String delete(@PathVariable(value = "idUser") Long id) {
        userService.deleteUser(id);
        return "User Deleted";
    }
}
