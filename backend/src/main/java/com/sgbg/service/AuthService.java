package com.sgbg.service;


import com.sgbg.domain.Auth;
import com.sgbg.domain.User;
import com.sgbg.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService {

    @Autowired
    AuthRepository authRepository;

    public boolean isUser(String kakaoId) {
        Long kakaoNumber = Long.valueOf(kakaoId);
        Optional<Auth> findByKakaoNumber = authRepository.findByKakaoNumber(kakaoNumber);

        System.out.println(findByKakaoNumber);
        System.out.println(findByKakaoNumber.isPresent());
        return findByKakaoNumber.isPresent();
    }

    public void createAuth(User user, String kakaoId) {
        System.out.println("Create Auth....");

        Auth auth = Auth.builder()
                .id(1L)
                .kakaoNumber(Long.valueOf(kakaoId))
                .build();

        Auth auth1 = authRepository.save(auth);
        System.out.println(auth1.getId());
        System.out.println(auth1.getKakaoNumber());
    }
}
