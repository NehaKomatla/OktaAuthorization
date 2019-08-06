package com.example.security;

import java.io.IOException;

public interface AuthService {

    AuthResponse userAuthenticate(String userName, String password) throws IOException;

    AuthResponse getRefreshedTokens(String refreshToken) throws IOException;

}
