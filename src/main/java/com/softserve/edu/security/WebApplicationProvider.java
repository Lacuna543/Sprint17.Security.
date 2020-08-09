package com.softserve.edu.security;

import com.softserve.edu.model.User;
import com.softserve.edu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class WebApplicationProvider implements AuthenticationProvider {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public WebApplicationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email);
//        System.out.println(passwordEncoder.encode("qwerty"));

    if (user == null) throw new UsernameNotFoundException("User with this email doesn't exist");

        String password = authentication.getCredentials().toString();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("Wrong password for " + email);
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
