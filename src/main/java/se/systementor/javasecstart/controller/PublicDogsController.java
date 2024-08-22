package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.systementor.javasecstart.model.DogRepository;
import se.systementor.javasecstart.services.DogService;

@Controller
public class PublicDogsController {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private DogService dogService;

    @GetMapping(path="/dogs")
    String list(Model model,
                @RequestParam(defaultValue = "1") int pageNo,
                @RequestParam(defaultValue = "10") int pageSize,
                @RequestParam(defaultValue = "") String sortCol,
                @RequestParam(defaultValue = "ASC") String sortOrder,
                @RequestParam(defaultValue = "") String searchTerm
    ) {
        dogService.sortDogs(model, pageNo, pageSize, searchTerm, sortCol, sortOrder, dogService);
        return "/dogs";
    }
}
