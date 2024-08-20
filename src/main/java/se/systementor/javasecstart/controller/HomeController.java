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
    String empty(Model model)
    {
        model.addAttribute("activeFunction", "home");
//        setupVersion(model);
        return "home";
    }



}



