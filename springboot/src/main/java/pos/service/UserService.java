package pos.service;

import java.util.ArrayList;
import java.util.List;

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

    public RoleService getRoleService() { return this.roleService;}

    public List<UserDTO> get() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDTO> usersDto = new ArrayList<>();
        for (User u: users) {
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
        if (validateCreate(user)) {
            user.setPassword(CryptoUtil.hash(user.getPassword()));
            user.setEmail(user.getEmail().toLowerCase());
            return userRepository.save(user);
        } else {
            throw new Exception();
        }
    }

    private boolean validateCreate(User user) {

        return true;
    }

    public User update(Integer id, User user) throws Exception {
        if (this.validateUpdate(id, user)) {
            return userRepository.save(user);
        } else {
            throw new Exception();
        }
    }

    private boolean validateUpdate(Integer id, User user) {
        User userTemporary = this.getRepository().findUserById(id);
        if(!userTemporary.getEmail().equals(user.getEmail())){
           return  false;
        }
       if (userTemporary.getName().equals(user.getName()));
           if (userTemporary.getName().equals(user.getName()));
               if (userTemporary.getCpf().equals(user.getCpf()));
                    if (userTemporary.getPhone().equals(user.getPhone()));
                         if (userTemporary.getRole().equals(user.getRole()));
               return true;
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

	public void delete(Integer id) {
        getRepository().delete(getRepository().findUserById(id));;
	}
}
