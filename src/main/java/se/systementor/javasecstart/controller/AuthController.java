package se.systementor.javasecstart.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.systementor.javasecstart.DTO.UserDTO;
import se.systementor.javasecstart.services.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/registerAccount")
    String registerAccount(Model model){
        return userService.loadRegisterPageModelAttributes(model);
    }

    @PostMapping("registerAccount")
    String registerAccount(@ModelAttribute("newUser") UserDTO dto, RedirectAttributes rda) throws MessagingException {
        return userService.registrationProcess(dto, rda);
    }

    @GetMapping("/login")
    String logins(Model model){
        if(!model.containsAttribute("message")){
            model.addAttribute("message", "");
        }
        return "login";
    }

    @PostMapping("/login")
    String logins(@RequestParam("username") String username, @RequestParam("password") String password){
        try {
            if (!userService.checkValidateUser(username, password)) {
                return "redirect:/login?error";
            } else {
                return "redirect:/home";
            }
        }
        catch (Exception e) {
            return "redirect:/login?userNotFound";
        }
    }

    @GetMapping("/verify/{tokenId}")
    String verify(@PathVariable("tokenId") String tokenId, RedirectAttributes rda){
        return userService.verifyUser(tokenId, rda);
    }
}
