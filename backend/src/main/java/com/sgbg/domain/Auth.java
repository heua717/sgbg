package com.sgbg.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * 인증 Entity
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Auth {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long id;

    private Long kakaoNumber; // 카카오 고유 회원 번호

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User user;

//    @Builder
//    public Auth(Long kakaoNumber) {
//        this.kakaoNumber = kakaoNumber;
////        this.user = user;
//    }
}

