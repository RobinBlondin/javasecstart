package se.systementor.javasecstart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.model.DogRepository;
import se.systementor.javasecstart.services.DogService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AdminDogController {

    private final DogService dogService;

    @GetMapping(path="/admin/dogs")
    String list(Model model,
                @RequestParam(defaultValue = "1") int pageNo,
                @RequestParam(defaultValue = "10") int pageSize,
                @RequestParam(defaultValue = "") String searchTerm,
                @RequestParam(defaultValue = "") String sortCol,
                @RequestParam(defaultValue = "ASC") String sortOrder

    ) {
        dogService.sortDogs(model, pageNo, pageSize, searchTerm, sortCol, sortOrder, dogService);
        return "admin/dogs/list";
    }


    @GetMapping("/admin/dogs/edit/{id}")
    public String editDog(@PathVariable("id") UUID id, Model model) {
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
