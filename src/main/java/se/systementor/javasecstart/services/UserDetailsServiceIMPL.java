package se.systementor.javasecstart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.model.User;
import se.systementor.javasecstart.model.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceIMPL implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.getUserByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return user.get();
    }
}
