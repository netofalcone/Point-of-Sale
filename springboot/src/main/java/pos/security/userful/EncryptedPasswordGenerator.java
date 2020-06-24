package pos.security.userful;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncryptedPasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }

}
