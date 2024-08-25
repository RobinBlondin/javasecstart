package se.systementor.javasecstart.services;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.systementor.javasecstart.DTO.UserDTO;
import se.systementor.javasecstart.model.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;

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

    public String registrationProcess(UserDTO userDTO, RedirectAttributes rda) throws MessagingException {
        Optional<User> user = userRepository.getUserByUsername(userDTO.getEmail());
        if(user.isPresent()) {
            rda.addFlashAttribute("newUser", userDTO);
            rda.addFlashAttribute("message", "Denna email är redan registrerad hos oss");
            return "redirect:/registerAccount";
        }

        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            rda.addFlashAttribute("newUser", userDTO);
            rda.addFlashAttribute("message", "Lösenorden matchar inte");
            return "redirect:/registerAccount";
        }

        Token token = createAndSaveNewUser(userDTO);
        emailService.sendVerificationEmail(userDTO.getName(), userDTO.getEmail(), token.getToken());
        rda.addFlashAttribute("message", "Ett email har skickats till dig för att verifiera ditt konto");
        return "redirect:/login";
    }

    public String loadRegisterPageModelAttributes(Model model) {
        if(!model.containsAttribute("newUser")) {
            model.addAttribute("newUser", new UserDTO());
        }

        if (!model.containsAttribute("message")) {
            model.addAttribute("message", "");
        }

        return "registerAccount";
    }

    public Token createAndSaveNewUser(UserDTO userDTO){
        Optional<Role> role = roleRepository.findByName("CLIENT");
        User user = dtoToUser(userDTO);
        Token token = new Token();
        token.setUser(user);

        Set<Role> roles = new HashSet<>();
        roles.add(role.orElse(null));
        user.setRoles(roles);

        userRepository.save(user);
        tokenRepository.save(token);
        return token;
    }

    public String hashPassword (String password){
     return new BCryptPasswordEncoder().encode(password);
    }

    public Boolean checkValidateUser (String user ,String password){
        Optional<User> user1 = userRepository.getUserByUsername(user);
        if(user1.isEmpty()) {
            return false;
        }
        String hashedPassword = hashPassword(password);
        return hashedPassword.equals(user1.get().getPassword());
    }

    public String verifyUser(String tokenId, RedirectAttributes rda){
        Optional<Token> token = tokenRepository.findByToken(tokenId);

        if(token.isEmpty()){
            rda.addFlashAttribute("message", "Okänd token");
            return "redirect:/login";
        }

        User user = token.get().getUser();
        if(token.get().isExpired()){;
            tokenRepository.delete(token.get());
            userRepository.delete(user);
            rda.addFlashAttribute("message", "Verifieringsperioden har utgått. Registrera dig igen");
            return "redirect:/login";
        }

        user.setEnabled(true);
        tokenRepository.delete(token.get());
        userRepository.save(user);
        rda.addFlashAttribute("message", "Ditt konto är nu verifierat. Du kan nu logga in!");
        return "redirect:/login";
    }

}
