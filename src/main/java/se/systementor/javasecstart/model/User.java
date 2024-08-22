package se.systementor.javasecstart.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 1, max = 100, message = "Ditt namn är för långt eller för kort. Minst 1 bokstav och högst 100")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Namnet får enbart innehålla bokstäver")
    private String name;

    @Email (message = "Din mailadress måste innehålla minst ett @ och en punkt.")
    private String username;

    @Size(min = 6, max = 100, message = "Lösenordet måste vara mellan 6 och 100 tecken långt")
    private String password;

    @Pattern(regexp = "\\d+", message = "Telefonnumret får enbart innehålla siffror")
    @Size(min = 9, max = 12, message = "Telefonnumret måste vara mellan 9 och 12 siffror")
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles;
}