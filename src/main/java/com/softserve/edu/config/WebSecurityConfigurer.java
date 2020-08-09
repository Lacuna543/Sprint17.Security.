package com.softserve.edu.config;

import com.softserve.edu.security.WebApplicationProvider;
import com.softserve.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final String USER = "TRAINEE";
    private final String MENTOR = "MENTOR";

    private WebApplicationProvider webApplicationProvider;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setWebApplicationProvider(WebApplicationProvider webApplicationProvider) {
        this.webApplicationProvider = webApplicationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().and().cors().disable()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/create-marathon",
//                        "/marathons",
                        "/marathons/edit/{id}",
                        "/marathons/delete/{id}",
                        "/students/{marathon_id}",
                        "/create-sprint/{marathon_id}",
                        "/create-student",
                        "/students/{marathon_id}/add",
                        "/students/{marathon_id}/edit/{student_id}",
                        "/students/{marathon_id}/delete/{student_id}",
                        "/students",
                        "/students/edit/{id}",
                        "/students/delete/{id}")
                .hasRole(MENTOR)
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/forbidden");


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(webApplicationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
