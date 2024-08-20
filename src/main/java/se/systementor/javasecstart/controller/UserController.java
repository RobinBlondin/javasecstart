package se.systementor.javasecstart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.systementor.javasecstart.DTO.UserDTO;
import se.systementor.javasecstart.model.Role;
import se.systementor.javasecstart.model.RoleRepository;
import se.systementor.javasecstart.model.User;
import se.systementor.javasecstart.model.UserRepository;
import se.systementor.javasecstart.services.UserService;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping("/registerAccount")
    String registerAccount(Model model){
        model.addAttribute("newUser", new UserDTO());
        return "registerAccount";
    }

    @PostMapping("registerAccount")
    String registerAccount(@ModelAttribute("newUser") UserDTO dto) {
        Role role = roleRepository.findByName("Client");
        User user = userService.dtoToUser(dto);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
        return "home";
    }

}
