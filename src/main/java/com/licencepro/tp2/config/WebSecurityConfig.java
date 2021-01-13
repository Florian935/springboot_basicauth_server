package com.licencepro.tp2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String HOME_URI = "/api/v1.0/home/**";

        http.
                authorizeRequests()
                    .antMatchers(HOME_URI).permitAll()
                    .anyRequest().authenticated()
                    .and()
                .httpBasic();
    }
}
