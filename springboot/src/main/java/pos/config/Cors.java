package pos.config;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class Cors {

    /*Mapeamento global que refletem em todo o sistema */
    @CrossOrigin
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/user/")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

}
