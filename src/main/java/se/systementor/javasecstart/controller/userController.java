package se.systementor.javasecstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class userController {
    @GetMapping("/registerAccount")
    String registerAccount(Model model){
        model.addAttribute("newUser", )
        return "registerAccount";
    }
}
