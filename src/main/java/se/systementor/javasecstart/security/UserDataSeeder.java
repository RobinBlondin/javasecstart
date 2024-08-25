package se.systementor.javasecstart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.model.Role;
import se.systementor.javasecstart.model.RoleRepository;
import se.systementor.javasecstart.model.User;
import se.systementor.javasecstart.model.UserRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDataSeeder {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;


    public void Seed(){
        if (roleRepo.findByName("Admin") == null) {
            addRole("Admin");
        }
        if (roleRepo.findByName("Client") == null) {
            addRole("Client");
        }
        if(userRepo.getUserByUsername("admin@doggo.se") == null){
            addUser("admin@doggo.se","Admin");
        }
        if(userRepo.getUserByUsername("client@doggo.se") == null){
            addUser("client@doggo.se","Client");
        }
    }

    private void addUser(String mail, String group) {
        Set<Role> roles = new HashSet<>();
        Optional<Role> role = roleRepo.findByName(group);

        role.ifPresent(roles::add);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("123");
        User user = User.builder().password(hash).enabled(true).username(mail).roles(roles).build();
        userRepo.save(user);
    }

    private void addRole(String name) {
        roleRepo.save(Role.builder().name(name).build());
    }

}