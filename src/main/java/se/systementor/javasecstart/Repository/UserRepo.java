package se.systementor.javasecstart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.systementor.javasecstart.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
