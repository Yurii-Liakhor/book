package com.example.book.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void init(WebSecurity web) throws Exception {
        super.init(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                    .authorizeRequests()
                        .antMatchers("/books/**").authenticated()
                        .antMatchers("/shop/**").authenticated()
                        .antMatchers("/orders/**").authenticated()
                    .anyRequest()
                        .permitAll()
                .and()
                    .formLogin()
                .and()
                    .httpBasic();
    }
}
