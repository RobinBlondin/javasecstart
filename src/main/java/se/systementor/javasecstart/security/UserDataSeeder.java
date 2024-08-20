package se.systementor.javasecstart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.model.Role;
import se.systementor.javasecstart.model.RoleRepository;
import se.systementor.javasecstart.model.User;
import se.systementor.javasecstart.model.UserRepository;

import java.util.HashSet;
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
        if(userRepo.getUserByEmail("admin@koriander.se") == null){
            addUser("admin@doggo.se","Admin");
        }
        if(userRepo.getUserByEmail("reception@koriander.se") == null){
            addUser("client@doggo.se","Client");
        }
    }

    private void addUser(String mail, String group) {
        Set<Role> roles = new HashSet<>();
        Role role = roleRepo.findByName(group);

        if(role != null) {
            roles.add(role);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("Hejsan123");
        User user = User.builder().password(hash).name(mail).roles(roles).build();
        userRepo.save(user);
    }

    private void addRole(String name) {
        roleRepo.save(Role.builder().name(name).build());
    }

}