package se.systementor.javasecstart.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface DogRepository extends CrudRepository<Dog, Long> {
    List<Dog> findAllBySoldToIsNull();
    Dog findById(UUID id);
}