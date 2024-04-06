package by.zemich.authorizationserver.core.model;
import by.zemich.authorizationserver.core.model.enums.Role;
import by.zemich.authorizationserver.core.model.enums.Status;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
public class User implements UserDetails {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Status status;
    private String viberChannelApiKey;
    private String telegramUsername;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
        return true;
    }

    @Override
    public boolean isEnabled() {
        return switch (status){
            case DEACTIVATED, WAITS_FOR_ACTIVATION ->  false;
            case ACTIVE -> true;
        };
    }


}
