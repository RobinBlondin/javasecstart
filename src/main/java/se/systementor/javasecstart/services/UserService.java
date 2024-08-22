package se.systementor.javasecstart.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
                .username(dto.getEmail())
                .password(hashPassword(dto.getPassword())).build();
    }

    public UserDTO userToDto (User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getUsername())
                .password(user.getPassword()).build();
    }

    public String hashPassword (String password){
     return new BCryptPasswordEncoder().encode(password);
    }

    public Boolean checkValidateUser (String user ,String password){
        User user1 = userRepository.getUserByUsername(user);
        if(user1 == null)throw new UsernameNotFoundException("User not found");
        String hashedPassword = hashPassword(password);
        return hashedPassword.equals(user1.getPassword());
    }

}
