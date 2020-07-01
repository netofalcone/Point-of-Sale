package pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pos.core.util.CryptoUtil;


@SpringBootApplication
@EntityScan(basePackages = {"pos.model"})
@EnableJpaRepositories(basePackages = {"pos.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableCaching
public class PosApplication {
	public static void main(String[] args) {
		SpringApplication.run(PosApplication.class, args);
	}
}
