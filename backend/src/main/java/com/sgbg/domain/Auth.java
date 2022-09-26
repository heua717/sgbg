package com.sgbg.domain;

import lombok.*;

import javax.persistence.*;

/**
 * 인증 Entity
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long id;

    private Long kakaoNumber; // 카카오 고유 회원 번호

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Auth(Long kakaoNumber, User user) {
        this.kakaoNumber = kakaoNumber;
        this.user = user;
    }
}

