package se.systementor.javasecstart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.model.DogRepository;

import java.util.List;
import java.util.UUID;

@Service
public class DogService {
    @Autowired
    DogRepository dogRepository;

    private String validateSortOrder(String sortOrder) {
        List<String> listOfValidSortOrders = List.of("asc", "desc");
        return listOfValidSortOrders.contains(sortOrder)? sortOrder : "asc";
    }

    private String validateSortCol(String sortCol) {
        List<String> listOfValidSortCols = List.of("name", "breed", "age", "size", "price");
        return listOfValidSortCols.contains(sortCol)? sortCol : "name";
    }

    private Page<Dog> getDogsPage(int pageNo, int pageSize, String sortCol, String sortOrder, String searchTerm) {
        sortCol = validateSortCol(sortCol);
        sortOrder = validateSortOrder(sortOrder);

        searchTerm = searchTerm.trim();

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortCol);
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);

        Page<Dog> dogPage;
        if (!searchTerm.isEmpty()) {
            dogPage = dogRepository.findAllByNameContainsOrBreedContains(searchTerm, searchTerm, pageable);
        } else {
            dogPage = dogRepository.findAll(pageable);
        }

        return dogPage;
    }

    public void sortDogs(Model model,
                                @RequestParam(defaultValue = "1") int pageNo,
                                @RequestParam(defaultValue = "10") int pageSize,
                                @RequestParam(defaultValue = "") String searchTerm,
                                @RequestParam(defaultValue = "") String sortCol,
                                @RequestParam(defaultValue = "ASC") String sortOrder,
                                DogService dogService) {

        Page<Dog> dogPage = dogService.getDogsPage(pageNo, pageSize, sortCol, sortOrder, searchTerm);

        model.addAttribute("dogs", dogPage.getContent());
        model.addAttribute("totalPages", dogPage.getTotalPages());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("searchTerm", searchTerm);
    }

    public List<Dog> getPublicDogs(){
        return dogRepository.findAllBySoldToIsNull();
    }

    public Dog getDogById(UUID id) {
        return dogRepository.findById(id);
    }

    public void saveDog(Dog dog) {
        dogRepository.save(dog);
    }
}
