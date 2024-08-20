package se.systementor.javasecstart;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.systementor.javasecstart.security.UserDataSeeder;

@SpringBootApplication
public class JavasecstartApplication {

    @Autowired
    UserDataSeeder userDataSeeder;
    public static void main(String[] args) {
        SpringApplication.run(JavasecstartApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            userDataSeeder.Seed();
        };
    }
}
