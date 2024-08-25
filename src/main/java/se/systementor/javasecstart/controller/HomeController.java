package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.systementor.javasecstart.services.UserService;
import se.systementor.javasecstart.model.DogRepository;

@Controller
public class HomeController {
    @GetMapping(path="/")
    String empty(Model model) {
        model.addAttribute("activeFunction", "home");
        return "home";
    }
}



