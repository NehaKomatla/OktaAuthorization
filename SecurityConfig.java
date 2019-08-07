package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * OAuth ignores the configuration below
     * 
     * @See ResourceServerConfig to control Authorization
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/user/authenticate/**").permitAll()
            .antMatchers("/user/greet/**").permitAll()
            .antMatchers("/user/getRefreshedTokens/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return new BCryptPasswordEncoder();
    // }
    //
    // @Bean
    // @Override
    // protected AuthenticationManager authenticationManager() throws Exception {
    // return super.authenticationManager();
    // }

}
