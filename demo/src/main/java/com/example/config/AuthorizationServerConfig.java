package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Configuration for Authorization Server
 * 
 * @author prasanna
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    static final String CLIENT_SECRET = "fXMuUOUo31nNlbwjpn64019h3JP-s6VZEOV4VGW1";

    static final String CLIENT_ID = "0oaz4m4nlMGDMqXB1356";

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.inMemory()
            .withClient(CLIENT_ID)
            .secret(CLIENT_SECRET)
            .authorizedGrantTypes(new String[] { "password", "refresh_token" })
            .accessTokenValiditySeconds(1800);
    }

}
