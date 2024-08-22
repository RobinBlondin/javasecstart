package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.model.DogRepository;

@Controller
public class PublicDogsController {

    @Autowired
    private DogRepository dogRepository;

    @GetMapping(path="/dogs")
    String list(Model model,
                @RequestParam(defaultValue = "1") int pageNo,
                @RequestParam(defaultValue = "10") int pageSize,
                @RequestParam(defaultValue = "name") String sortCol,
                @RequestParam(defaultValue = "ASC") String sortOrder,
                @RequestParam(defaultValue = "") String searchTerm
    ) {
//        model.addAttribute("activeFunction", "home");
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

//        model.addAttribute("dogs", dogService.getPublicDogs());
        model.addAttribute("dogs", dogPage.getContent());
        model.addAttribute("totalPages", dogPage.getTotalPages());
        model.addAttribute("pageNo", pageNo);
        return "/dogs";
    }
}
