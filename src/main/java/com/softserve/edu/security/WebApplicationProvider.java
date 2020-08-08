package com.softserve.edu.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class WebApplicationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        //String roles = authentication.getAuthorities();
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password );
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
