package com.sgbg.common.util;

import com.sgbg.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CookieUtil {

    private final RedisService redisService;

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

    public Map<String, String> getTokenInfo(HttpServletRequest request) {
        Map<String, String> tokens = new HashMap<>();

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if ("accessToken".equals(c.getName())) {
                tokens.put("access_token", c.getValue());
            } else if ("refreshToken".equals(c.getName())) {
                tokens.put("refresh_token", c.getValue());
            }
        }
        return tokens;
    }
}
