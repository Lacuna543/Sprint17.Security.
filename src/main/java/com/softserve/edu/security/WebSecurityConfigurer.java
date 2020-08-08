package com.softserve.edu.security;

import com.softserve.edu.model.User;
import com.softserve.edu.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

    //   User user = userRepository.
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
      //  builder.inMemoryAuthentication().withUser("mike").password("{noop}1111").roles("admin");
        //  builder.jdbcAuthentication().dataSource();
        builder.authenticationProvider();
    }
}

