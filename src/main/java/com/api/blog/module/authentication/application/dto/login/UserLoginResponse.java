package com.api.blog.module.authentication.application.dto.login;

import com.api.blog.module.user.domain.enumerator.RoleUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public record UserLoginResponse(long id, String email, Collection<? extends GrantedAuthority> roles, String token){
}
