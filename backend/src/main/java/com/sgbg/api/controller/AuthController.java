package com.sgbg.api.controller;

import com.sgbg.api.response.AuthRes;
import com.sgbg.service.KakaoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "사용자 인증 API", tags = {"Auth"})
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    KakaoService kakaoService;

    @GetMapping("/login")
    public ResponseEntity<? extends AuthRes> kakaoLogin(@RequestParam String code, HttpServletResponse response) throws IOException {
        String kakaoEmail = null;

        String accessToken = kakaoService.getKakaoAccessToken(code);
        kakaoService.getKakaoUserInfo(accessToken);

        return ResponseEntity.status(200).body(AuthRes.of(200, "Success", accessToken, true, 1L));
    }

}
