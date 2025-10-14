package com.api.blog.module.user.domain.enumerator;

import org.springframework.security.core.GrantedAuthority;

public enum RoleUser implements GrantedAuthority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String authority;

    RoleUser(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
