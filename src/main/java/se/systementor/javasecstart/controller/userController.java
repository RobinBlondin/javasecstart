package se.systementor.javasecstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.systementor.javasecstart.DTO.UserDTO;
import se.systementor.javasecstart.model.Role;
import se.systementor.javasecstart.model.User;
import se.systementor.javasecstart.model.UserRepository;
import se.systementor.javasecstart.services.UserService;

@Controller
public class userController {
    UserRepository userRepository;
    UserService userService;

    @GetMapping("/registerAccount")
    String registerAccount(Model model){
        model.addAttribute("newUser", new UserDTO());
        return "registerAccount";
    }

    @PostMapping("registerAccount")
    String registerAccount(@ModelAttribute("newUser") UserDTO dto){
        Role role = new Role("CLIENT");
        User user = userService.dtoToUser(dto);


        userRepository.save(user);
        return "home";
    }

}
