package com.api.blog.core;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthenticationService {

    long getAuthenticatedUserId();

    UserDetails getUserDetails();

}