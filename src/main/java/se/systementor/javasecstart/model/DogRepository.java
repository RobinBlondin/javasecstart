package se.systementor.javasecstart.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface DogRepository extends CrudRepository<Dog, Long> {
    Page<Dog> findAll(Pageable pageable);
    List<Dog> findAllBySoldToIsNull();
    Dog findById(UUID id);
    Page<Dog> findAllByNameContainsOrBreedContains(String name, String breed, Pageable pageable);
}