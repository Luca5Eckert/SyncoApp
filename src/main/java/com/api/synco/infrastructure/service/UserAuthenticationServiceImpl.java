package com.api.synco.infrastructure.service;

import com.api.synco.core.UserAuthenticationService;
import com.api.synco.infrastructure.security.user_details.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Override
    public long getAuthenticatedUserId() {
        var userDetails = (UserDetailsImpl) getUserDetails();

        return userDetails.getId();
    }

    @Override
    public UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
