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
    @Autowired
    private DogRepository dogRepository;

    UserService userService = new UserService();

    @GetMapping(path="/")
    String empty(Model model)
    {
        model.addAttribute("activeFunction", "home");
//        setupVersion(model);
        return "home";
    }

    @GetMapping("/logins")
    String logins(Model model){
        return "logins";
    }

    @PostMapping("/logincheck")
    String logins(Model model, @RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println("Utanför");
        try {
            if (!userService.checkValidateUser(username, password)) {
                System.out.println("Fel användare");
                return "redirect:/logins?error";
            } else {
                return "redirect:/home";
            }
        }
        catch (Exception e) {
            return "redirect:/logins?userNotFound";
        }

    }



}



