package com.api.synco.infrastructure.security.user_details;

import com.api.synco.module.user.domain.exception.UserNotFoundException;
import com.api.synco.module.user.domain.port.UserRepository;
import com.api.synco.module.user.domain.vo.Email;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDetailsMapper userDetailsMapper;

    public UserDetailsServiceImpl(UserRepository userRepository, UserDetailsMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(new Email(email)).orElseThrow( () -> new UserNotFoundException(email));
        return userDetailsMapper.toEntity(user);
    }

}
