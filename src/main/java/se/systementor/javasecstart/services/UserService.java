package se.systementor.javasecstart.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.DTO.UserDTO;
import se.systementor.javasecstart.model.User;
import se.systementor.javasecstart.model.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    public User dtoToUser(UserDTO dto){
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .password(hashPassword(dto.getPassword())).build();
    }

    public UserDTO userToDto (User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .password(user.getPassword()).build();
    }

    public String hashPassword (String password){
     return new BCryptPasswordEncoder().encode(password);
    }

}
