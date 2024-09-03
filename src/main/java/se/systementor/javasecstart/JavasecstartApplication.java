package se.systementor.javasecstart;

import io.github.cdimascio.dotenv.Dotenv;
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
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        return args -> {
            userDataSeeder.Seed();
        };
    }
}
