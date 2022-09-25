package com.sgbg.common.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class CookieUtil {

    // TODO: HTTPS 적용 후, secure 설정 변경
    
    public Cookie addAccessToken(String accessToken) {
        Cookie cookie = new Cookie("accessToken", accessToken);
        cookie.setMaxAge((int) System.currentTimeMillis() * 1800 * 1000);
//        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    public Cookie addRefreshToken(String refreshToken) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setMaxAge(86400 * 1000);
//        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
}
