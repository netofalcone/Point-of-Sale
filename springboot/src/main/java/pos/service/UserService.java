package pos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pos.core.util.CryptoUtil;
import pos.dto.RoleDTO;
import pos.dto.UserDTO;
import pos.model.Role;
import pos.model.User;
import pos.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public UserRepository getRepository() {
        return this.userRepository;
    }

    public RoleService getRoleService() {
        return this.roleService;
    }

    public List<UserDTO> get() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDTO> usersDto = new ArrayList<>();
        for (User u : users) {
            usersDto.add(toUserDto(u));
        }
        return usersDto;
    }

    public User userEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Integer id) {
        return userRepository.findUserById(id);
    }

    public User create(User user) throws Exception {
        validateCreate(user);
        user.setPassword(CryptoUtil.hash(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return userRepository.save(user);
    }

    private void validateCreate(User user) throws Exception {
        if (user.getName().length() < 3 | user.getName().length() > 250 | !user.getName().matches("[a-zA-Z\\s]*")) {
            throw new Exception("name.invalid");
        }
        if (getRepository().findByCpf(user.getCpf()) != null) {
            throw new Exception("cpf.exists");
        }
        if (getRepository().findByEmail(user.getEmail()) != null) {
            throw new Exception("email.exists");
        }
        if (!user.getEmail().matches("[a-zA-Z._-]+@+[a-zA-Z.]+") | user.getEmail() == null) {
            throw new Exception("email.invalid");
        }
        if (user.getCpf().length() != 11) {
            throw new Exception("cpf.invalid");
        }
        if (user.getPassword() == null | !user.getPassword().matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@!#]{8,}$")) {
            throw new Exception("password.invalid");
        }
        if (user.getPhone() != null) {
            if (!user.getPhone().matches("[0-9]+") | user.getPhone().length() < 7 | user.getPhone().length() > 12) {
                throw new Exception("phone.invalid");
            }
        }
        if (user.getRole() == null) {
            throw new Exception("role.invalid");
        }
    }

    public User update(User user) throws Exception {
        this.validateUpdate(user);
        return userRepository.save(user);
    }

    private void validateUpdate(User user) throws Exception {
        User userTemporary = this.findUserbyEmail(user.getEmail());

        if (!userTemporary.getPassword().equals(user.getPassword())) {
            String passwordCrypted = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(passwordCrypted);
        }
        if (user.getName().length() < 3 | user.getName().length() > 250 | !user.getName().matches("[a-zA-Z\\s]*")) {
            throw new Exception("name.invalid");
        }
        if (getRepository().findByCpf(user.getCpf()) != null & userTemporary.getCpf() != user.getCpf()) {
            throw new Exception("cpf.exists");
        }
        if (getRepository().findByEmail(user.getEmail()) != null & userTemporary.getEmail() != user.getEmail()) {
            throw new Exception("email.exists");
        }
        if (!user.getEmail().matches("[a-zA-Z._-]+@+[a-zA-Z.]+") | user.getEmail() == null) {
            throw new Exception("email.invalid");
        }
        if (user.getCpf().length() != 11) {
            throw new Exception("cpf.invalid");
        }
        if (user.getPhone() != null) {
            if (!user.getPhone().matches("[0-9]+") | user.getPhone().length() < 7 | user.getPhone().length() > 12) {
                throw new Exception("phone.invalid");
            }
        }
        if (user.getRole() == null) {
            throw new Exception("role.invalid");
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserbyEmail(String email) {
        return getRepository().findByEmail(email);
    }

    public User findByEmailAndPassword(String username, String password) {
        return getRepository().findByEmailAndPassword(username.toLowerCase(), CryptoUtil.hash(password));
    }

    public User toUserModel(UserDTO userDTO) {
        User user = new User();
        Role role = getRoleService().getRoleById(userDTO.getRole().getId());
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setCpf(userDTO.getCpf());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getNewPassword());

        user.setRole(role);
        return user;
    }

    public UserDTO toUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        Role role = getRoleService().getRoleById(user.getRole().getId());
        RoleDTO roleDTO = getRoleService().toRoleDTO(role);
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());

        userDTO.setRole(roleDTO);
        return userDTO;
    }
}
