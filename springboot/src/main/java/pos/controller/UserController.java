package pos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pos.dto.UserDTO;
import pos.model.User;
import pos.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    public UserService getService() {
        return this.userService;
    }

    @GetMapping(value = "", produces = "Application/json")
    public ResponseEntity<List<UserDTO>> list() {
        List<UserDTO> users = userService.get();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "Application/json")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) {
        Optional<User> user = userService.findById(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "Application/json")
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) throws Exception {
        User userSalved = userService.create(getService().toUserModel(userDTO));
        return new ResponseEntity<>(userSalved, HttpStatus.CREATED);
    }

/*    @PutMapping(value = "/", produces = "Application/json")
    public ResponseEntity<User> update(@RequestBody User user) throws Exception {
        User userUpdate = userService.updateUser(user);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idUser}", produces = "Application/text")
    public ResponseEntity<?> delete(@PathVariable(value = "idUser") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
}
