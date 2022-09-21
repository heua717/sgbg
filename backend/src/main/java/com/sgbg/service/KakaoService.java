package com.sgbg.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
public class KakaoService {

    @Value("${kakao.client.id}")
    String CLIENT_ID;

    @Value("${kakao.client.secret}")
    String CLIENT_SECRET;

    @Value("${kakao.redirect.uri}")
    String REDIRECT_URI;

    public String getKakaoAccessToken(String code) {
        String access_token = "";
        String refresh_token = "";

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders tokenRequestHeader = new HttpHeaders(); // http 요청 헤더
            tokenRequestHeader.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            MultiValueMap<String, String> tokenRequestBody = new LinkedMultiValueMap<>(); // http 요청 body
            tokenRequestBody.add("grant_type", "authorization_code");
            tokenRequestBody.add("code", code);
            tokenRequestBody.add("client_id", CLIENT_ID);
            tokenRequestBody.add("client_secret", CLIENT_SECRET);
            tokenRequestBody.add("redirect_uri", REDIRECT_URI);

            HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenRequestBody, tokenRequestHeader);

            ResponseEntity<String> tokenResponse = restTemplate.exchange( // 인증 코드로 토큰 요청
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    tokenRequest,
                    String.class
            );

            // 토큰 전달 받음
            JSONObject jsonObject = new JSONObject(tokenResponse.getBody());

            access_token = jsonObject.getString("access_token");
            refresh_token = jsonObject.getString("refresh_token");
//            Integer expires_in = (Integer) jsonObject.get("expires_in");
//            Integer refresh_token_expires_in = (Integer) jsonObject.get("refresh_token_expires_in");
//            String token_type = jsonObject.getString("token_type");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return access_token;
    }

    public String getKakaoUserInfo(String access_token) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

            // 토큰으로 카카오 API 호출 (카카오 서버에서 토큰 유효성 확인후 사용자 데이터 받아옴)
            HttpHeaders apiRequestHeader = new HttpHeaders();
            apiRequestHeader.add("Authorization", "Bearer " + access_token);
            apiRequestHeader.add("Content-type", "application/x-www-form-urlencoded;charset=utf8");

            HttpEntity<HttpHeaders> apiRequest = new HttpEntity<>(apiRequestHeader);
            HttpEntity<String> apiResponse = restTemplate.exchange( // 토큰과 함께 api를 호출한다.
                    "https://kapi.kakao.com/v2/user/me",
                    HttpMethod.POST,
                    apiRequest,
                    String.class
            );

            JSONObject jsonObject2 = new JSONObject(apiResponse.getBody());

            System.out.println(jsonObject2.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
//        JSONObject kakao_account = (JSONObject) jsonObject2.get("kakao_account");
//        String email = kakao_account.getString("email");

        return "Hello";
    }
}
