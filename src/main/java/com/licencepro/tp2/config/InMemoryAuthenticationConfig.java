package com.licencepro.tp2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(2)
public class InMemoryAuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureInMemoryUserAuthentication(
            AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .authorities("ROLE_USER");
    }

    @Autowired
    public void configureInMemoryAdminAuthentication(
            AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder)
        throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .authorities("ROLE_ADMIN");
    }
}
