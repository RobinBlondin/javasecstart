package se.systementor.javasecstart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.Repository.RoleRepo;
import se.systementor.javasecstart.Repository.UserRepo;
import se.systementor.javasecstart.model.Role;

@Service
public class UserDataSeeder {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    public void seedUsers() {
        if (roleRepo.findByName("Admin") == null){
            addRole("Admin");
        }
    }

    private void addRole(String roleName) {
        roleRepo.save(Role.builder().name(name).build());
    }
}
