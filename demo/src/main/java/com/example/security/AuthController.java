package com.example.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.GET, value = "/authenticate")
    public AuthResponse userAuthenticate(
        @RequestParam(value = "userName") String userName,
        @RequestParam(value = "password") String password) throws IOException {

        return authService.userAuthenticate(userName, password);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRefreshedTokens")
    public AuthResponse getRefreshedTokens(@RequestParam(value = "refreshToken") String refreshToken)
        throws IOException {

        return authService.getRefreshedTokens(refreshToken);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/greet")
    public String greet() {
        return "Okta Authentication Successful";
    }

}
