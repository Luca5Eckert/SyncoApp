package com.api.synco.infrastructure.security.user_details;

import com.api.synco.module.user.domain.enumerator.RoleUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final long id;
    private final String email;
    private final String password;
    private final RoleUser roleUser;

    public UserDetailsImpl(long id, String email, String password, RoleUser roleUser) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleUser = roleUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(roleUser);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public long getId(){
        return id;
    }

}
