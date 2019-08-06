package com.example.security;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class AuthServiceImpl implements AuthService {

    private final MediaType jsonMediaType = okhttp3.MediaType.parse("application/json; charset=utf-8");

    private final MediaType encodedMediaType = okhttp3.MediaType.parse("application/x-www-form-urlencoded");

    @Override
    public AuthResponse userAuthenticate(String userName, String password) throws IOException {

        AuthResponse authResponse = new AuthResponse();

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody body = RequestBody.create(jsonMediaType, "{\"username\":\"" + userName + "\"," +
            " \"password\":\"" + password + "\"}");

        Request sessionTokenRequest = new Request.Builder()
            .url("https://itorizon.okta.com/api/v1/authn")
            .post(body)
            .build();

        Response sessionTokenResponse = okHttpClient.newCall(sessionTokenRequest).execute();

        if (sessionTokenResponse.code() == 200) {
            JSONObject jsonResponse = new JSONObject(new String(sessionTokenResponse.body().string()));
            String sessionToken = jsonResponse.optString("sessionToken");

            Request codeRequest = new Request.Builder()
                .url("https://itorizon.okta.com/oauth2/v1/authorize?client_id=0oaz4m4nlMGDMqXB1356"
                    + "&response_type=code"
                    + "&scope=openid offline_access"
                    + "&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fokta%2Fuser%2Fgreet"
                    + "&state=debug"
                    + "&nonce=test"
                    + "&sessionToken=" + sessionToken)
                .get()
                .build();

            Response codeResponse = okHttpClient.newCall(codeRequest).execute();

            String authorizationCode = codeResponse.request().url().queryParameter("code");

            RequestBody tokenRequestBody = RequestBody.create(encodedMediaType, "client_id=0oaz4m4nlMGDMqXB1356"
                + "&client_secret=fXMuUOUo31nNlbwjpn64019h3JP-s6VZEOV4VGW1"
                + "&grant_type=authorization_code"
                + "&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fokta%2Fuser%2Fgreet"
                + "&code=" + authorizationCode);

            Request tokenRequest = new Request.Builder()
                .url("https://itorizon.okta.com/oauth2/v1/token")
                .post(tokenRequestBody)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

            Response tokenResponse = okHttpClient.newCall(tokenRequest).execute();

            JSONObject tokenResponseBody = new JSONObject(new String(tokenResponse.body().string()));
            authResponse.setAccessToken(tokenResponseBody.optString("access_token"));
            authResponse.setIdToken(tokenResponseBody.optString("id_token"));
            authResponse.setRefreshToken(tokenResponseBody.optString("refresh_token"));
            authResponse.setSuccess(tokenResponse.isSuccessful());

            return authResponse;
        }

        authResponse.setSuccess(false);
        return authResponse;
    }

    @Override
    public AuthResponse getRefreshedTokens(String refreshToken) throws IOException {

        AuthResponse authResponse = new AuthResponse();

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody tokenRequestBody = RequestBody.create(encodedMediaType, "client_id=0oaz4m4nlMGDMqXB1356"
            + "&client_secret=fXMuUOUo31nNlbwjpn64019h3JP-s6VZEOV4VGW1"
            + "&grant_type=refresh_token"
            + "&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fokta%2Fuser%2Fgreet"
            + "&refresh_token=" + refreshToken);

        Request tokenRequest = new Request.Builder()
            .url("https://itorizon.okta.com/oauth2/v1/token")
            .post(tokenRequestBody)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build();

        Response tokenResponse = okHttpClient.newCall(tokenRequest).execute();

        if (tokenResponse.code() == 200) {
            JSONObject tokenResponseBody = new JSONObject(new String(tokenResponse.body().string()));
            authResponse.setAccessToken(tokenResponseBody.optString("access_token"));
            authResponse.setIdToken(tokenResponseBody.optString("id_token"));
            authResponse.setRefreshToken(tokenResponseBody.optString("refresh_token"));
            authResponse.setSuccess(tokenResponse.isSuccessful());
            return authResponse;

        }
        authResponse.setSuccess(false);
        return authResponse;

    }
}
