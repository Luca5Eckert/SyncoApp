package com.api.blog.module.authentication.application.dto.login;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UserLoginResponse(long id, String email, Collection<? extends GrantedAuthority> roles, String token){
}
