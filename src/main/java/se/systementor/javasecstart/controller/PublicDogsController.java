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
import se.systementor.javasecstart.services.DogService;

import java.util.List;

@Controller
public class PublicDogsController {

    @Autowired
    private DogService dogService;
    @Autowired
    private DogRepository dogRepository;


    @GetMapping(path="/dogs")
    String list(Model model){
        model.addAttribute("activeFunction", "publicdogs");
//        setupVersion(model);

        model.addAttribute("dogs", dogService.getPublicDogs());
        return "dogs";
    }


}
