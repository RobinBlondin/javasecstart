package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.model.DogRepository;

import java.util.List;

@Controller
public class HomeController {
//    @GetMapping(path="/")
//    String empty(Model model)
//    {
//        model.addAttribute("activeFunction", "home");
////        setupVersion(model);
//
////        model.addAttribute("dogs", dogRepository.findAll());
//        return "home";
//    }

    @Autowired
    private DogRepository dogRepository;

    @GetMapping(path="/")
    String empty(Model model, @RequestParam(defaultValue = "1") int pageNo,
                 @RequestParam(defaultValue = "10") int pageSize,
                 @RequestParam(defaultValue = "name") String sortCol,
                 @RequestParam(defaultValue = "ASC") String sortOrder,
                 @RequestParam(defaultValue = "") String searchTerm)
    {
        model.addAttribute("activeFunction", "home");
//        setupVersion(model);

        searchTerm = searchTerm.trim();

        model.addAttribute("searchTerm", searchTerm);

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortCol);
//        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);

        if (!searchTerm.isEmpty()) {
            model.addAttribute("dogs", dogRepository.findAllByNameContainsOrBreedContains(searchTerm, searchTerm, sort));
            model.addAttribute("totalPages", 1);
            model.addAttribute("pageNo", 1);
        } else {
            List<Dog> page = dogRepository.findAll(sort);
            model.addAttribute("pageNo", pageNo);
            model.addAttribute("totalPages", 1);
            model.addAttribute("dogs", page);
        }
        return "home";
    }



}



