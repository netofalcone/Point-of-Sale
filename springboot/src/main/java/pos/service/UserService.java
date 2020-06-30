package pos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pos.core.util.CryptoUtil;
import pos.model.User;
import pos.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public List<User> get() {
        return (List<User>) userRepository.findAll();
    }

    public User userEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public User create(User user) throws Exception {
        if (validateInsert(user)) {
            user.setPassword(CryptoUtil.hash(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new Exception();
        }
    }

    private boolean validateInsert(User user) {
        String passwordCrypted = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordCrypted);

        return false;
    }

    public User updateUser(User user) throws Exception {
        if (this.validateUpdate(user)) {
            return userRepository.save(user);
        } else {
            throw new Exception();
        }
    }

    private boolean validateUpdate(User user) {
        User userTemporary = this.findUserbyEmail(user.getEmail());

        if (!userTemporary.getPassword().equals(user.getPassword())) {
            String passwordCrypted = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(passwordCrypted);
        }

        return false;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        return;
    }

    public User findUserbyEmail(String email) {
        return null;
    }

    public User findByEmailAndPassword(String username, String password) {
        return getUserRepository().findByEmailAndPassword(username.toUpperCase(), CryptoUtil.hash(password));
    }
}
