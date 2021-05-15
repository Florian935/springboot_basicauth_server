package com.licencepro.tp2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.licencepro.tp2.config.constant.RolesConstants.ROLE_ADMIN;
import static com.licencepro.tp2.config.constant.RolesConstants.ROLE_USER;

@Configuration
@Order(2)
public class InMemoryAuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureInMemoryAuthentication(
            AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                    .password(passwordEncoder.encode("password"))
                    .roles(ROLE_USER).and()
                .withUser("admin")
                    .password(passwordEncoder.encode("password"))
                    .roles(ROLE_ADMIN)
        ;
    }
}
