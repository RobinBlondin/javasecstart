package se.systementor.javasecstart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final DogRepository dogRepository;


    @GetMapping(path="/admin/dogs")
    String list(Model model,
                @RequestParam(defaultValue = "1") int pageNo,
                @RequestParam(defaultValue = "10") int pageSize,
                @RequestParam(defaultValue = "name") String sortCol,
                @RequestParam(defaultValue = "ASC") String sortOrder,
                @RequestParam(defaultValue = "") String searchTerm
    ) {
        model.addAttribute("activeFunction", "home");
//        setupVersion(model);
        searchTerm = searchTerm.trim();
        model.addAttribute("searchTerm", searchTerm);

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortCol);
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);

        Page<Dog> dogPage;
        if (!searchTerm.isEmpty()) {
            dogPage = dogRepository.findAllByNameContainsOrBreedContains(searchTerm, searchTerm, pageable);
        } else {
            dogPage = dogRepository.findAll(pageable);
        }

        model.addAttribute("dogs", dogPage.getContent());
        model.addAttribute("totalPages", dogPage.getTotalPages());
        model.addAttribute("pageNo", pageNo);
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
