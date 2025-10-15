package com.api.blog.infrastructure.service;

import com.api.blog.core.UserAuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Override
    public long getAuthenticatedUserId() {
        return 2;
    }
}
