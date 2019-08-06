package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * OAuth ignores the configuration below
     * 
     * @See ResourceServerConfig to control Authorization
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
            .and()
            .anonymous()
            .and()
            .authorizeRequests()
            .antMatchers("/user/authenticate/**").permitAll()
            .antMatchers("/user/greet/**").permitAll()
            .antMatchers("/user/getRefreshedTokens/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .realmName("UCBOS Realm")
            .and()
            .csrf()
            .disable();
    }
}
