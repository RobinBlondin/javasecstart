package se.systementor.javasecstart.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@AllArgsConstructor
@Builder
@Data
public class User implements UserDetails {
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

    private boolean enabled;

    public User() {
        this.name = null;
        this.username = null;
        this.password = null;
        this.phone = null;
        this.enabled = false;
        this.roles = new HashSet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : this.roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}