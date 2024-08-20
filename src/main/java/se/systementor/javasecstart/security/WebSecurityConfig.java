package se.systementor.javasecstart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/","/css/**", "/js/**","/images/**", "/public**", "/dogs","/registerAccount", "/admin/**", "admin/dogs/edit/**")
                .permitAll().requestMatchers("/users")
                .hasAuthority("Admin").anyRequest().authenticated()

        )
                .formLogin(formLogin -> formLogin
                       //.loginPage("/login")
                        //.loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout ->
                    logout.permitAll()
                            .logoutSuccessUrl("/")
                        )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                        .csrf(AbstractHttpConfigurer::disable);

                return http.build();

    }




    }

