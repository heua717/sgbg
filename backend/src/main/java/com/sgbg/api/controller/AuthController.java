package com.sgbg.api.controller;

import com.sgbg.api.response.AuthRes;
import com.sgbg.api.response.BaseResponseBody;
import com.sgbg.common.util.CookieUtil;
import com.sgbg.domain.Auth;
import com.sgbg.domain.User;
import com.sgbg.service.AuthService;
import com.sgbg.service.KakaoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@CrossOrigin("*")
@Api(value = "사용자 인증 API", tags = {"Auth"})
@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    KakaoService kakaoService;

    @Autowired
    AuthService authService;

    @Autowired
    CookieUtil cookieUtil;

    @GetMapping("/login")
    public ResponseEntity<? extends BaseResponseBody> kakaoLogin(@RequestParam String code, HttpServletResponse response) throws IOException {
        // 1. 인가 code로 Kakao Auth Server에서 token 받기
        try {
            Map<String, String> tokenInfo = kakaoService.getKakaoTokenInfo(code);

            // 2. access token으로 Kakao Resource Server에서 user 정보 갖고 오기
            Map<String, String> userInfo = kakaoService.getKakaoUserInfo(tokenInfo.get("access_token"));

            // 3. 회원 가입이 안되어 있는 경우, 회원가입 시키기
            String kakaoId = userInfo.get("id");
            System.out.println(kakaoId);
            if (!authService.isUser(kakaoId)) {
                // TODO: User 변경
//            User user = userService.createUser();
                User user = new User();
                authService.createAuth(user, kakaoId);
            }

            // TODO: 4. Redis Session에 token 과 user 정보 저장

            // - refresh token의 expire 시간을 session 만료 시간으로 설정

            // 5. Cookie에 token 저장
            String accessToken = tokenInfo.get("access_token");
            String refreshToken = tokenInfo.get("refresh_token");

            Cookie accessCookie = cookieUtil.addAccessToken(accessToken);
            Cookie refreshCookie = cookieUtil.addRefreshToken(refreshToken);

            response.addCookie(accessCookie);
            response.addCookie(refreshCookie);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(2010, "Success"));
    }

    @GetMapping("/logout")
    public ResponseEntity<? extends BaseResponseBody> logout(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = null;
        String refreshToken = null;
        String bearer = request.getHeader("Authorization");

//        if (bearer != null && !"".equals(bearer)) {
//            accessToken = bearer.split(" ")[1];
//        }

        Cookie[] cookies = request.getCookies();

        for (Cookie c : cookies) {
            if ("accessToken".equals(c.getName())) {
                accessToken = c.getValue();
            } else if ("refreshToken".equals(c.getName())) {
                refreshToken = c.getValue();
            }
        }

        String id = kakaoService.logout(accessToken);

        Cookie accessCookie = new Cookie("accessToken", null);
        accessCookie.setMaxAge(0);
        accessCookie.setPath("/");
        response.addCookie(accessCookie);

        Cookie refreshCookie = new Cookie("refreshToken", null);
        refreshCookie.setMaxAge(0);
        refreshCookie.setPath("/");
        response.addCookie(refreshCookie);

        // TODO: Redis Session 만료

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(2000, "Success"));
    }

    @GetMapping("/refresh")
    public void updateToken() {
        // TODO: update access/refresh token 구현

    }

}
