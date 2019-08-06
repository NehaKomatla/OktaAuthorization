package com.example.security;

public class AuthResponse {

    private String accessToken;

    private String idToken;

    private String refreshToken;

    boolean success;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "AuthResponse [accessToken=" + accessToken + ", idToken=" + idToken + ", refreshToken=" + refreshToken
            + ", success=" + success + "]";
    }

}
