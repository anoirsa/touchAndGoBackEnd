package com.example.springtouchgo.model.appuser;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@NoArgsConstructor
@ToString
// Because we are using the JPA, we need to annotate as Entity
@Entity
@Getter
public class AppUser implements UserDetails {
    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )

    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean isEnabled;
    private boolean isCredentialsNonExpired;
    private boolean isAccountNonLocked;
    private boolean isAccountNonExpired;
    @Enumerated(EnumType.STRING)
    private AppUserRole userRole;

    public AppUser(String username, String password,
                   String email, boolean isEnabled,
                   boolean isCredentialsNonExpired, boolean isAccountNonLocked,
                   boolean isAccountNonExpired, AppUserRole userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isEnabled = isEnabled;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isAccountNonExpired = isAccountNonExpired;
        this.userRole = userRole;
    }



    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return userRole.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
