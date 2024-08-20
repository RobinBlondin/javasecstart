package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.services.DogService;

@Controller
public class AdminDogController {
    @Autowired
    private DogService dogService;


    @GetMapping(path="/admin/dogs")
    String list(Model model){
        model.addAttribute("activeFunction", "home");
//        setupVersion(model);

        model.addAttribute("dogs", dogService.getPublicDogs());
        return "admin/dogs/list";
    }

    @GetMapping("/admin/dogs/edit/{id}")
    public String editDog(@PathVariable("id") Long id, Model model) {
        Dog dog = dogService.getDogById(id);
        model.addAttribute("dog", dog);
        return "/admin/dogs/edit";
    }

    @PostMapping("/admin/dogs/edit/update")
    public String editDog(@ModelAttribute("dog") Dog dog) {
        if(dog.getSoldTo().isEmpty()) {
            dog.setSoldTo(null);
        }

        dogService.saveDog(dog);
        System.out.println("dog updated");
        return "redirect:/admin/dogs";
    }

}
