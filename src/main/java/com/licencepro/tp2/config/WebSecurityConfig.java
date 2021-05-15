package com.licencepro.tp2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.licencepro.tp2.config.constant.RolesConstants.ROLE_ADMIN;
import static com.licencepro.tp2.config.constant.RolesConstants.ROLE_USER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String HOME_URI = "/api/v1.0/home/**";
        final String BOOK_URI = "/api/v1.0/books/**";

        http
                .csrf().disable()
                .authorizeRequests()
                    // home URI
                    .antMatchers(HOME_URI).permitAll()

                    // books URI
                    .antMatchers(PUT, BOOK_URI).hasRole(ROLE_ADMIN)
                    .antMatchers(DELETE, BOOK_URI).hasRole(ROLE_ADMIN)
                    .antMatchers(POST, BOOK_URI).hasRole(ROLE_ADMIN)
                    .antMatchers(BOOK_URI).hasAnyRole(ROLE_USER, ROLE_ADMIN)

                    .anyRequest().authenticated().and()
                .httpBasic();
 //               .and()
//                .sessionManagement().sessionCreationPolicy(STATELESS);
    }
}
