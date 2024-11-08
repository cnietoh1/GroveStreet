package es.carlosnh.grovestreet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class GroveStreetApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroveStreetApplication.class, args);
    }

}
