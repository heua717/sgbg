package com.sgbg.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private int hostScore;

    private int memberScore;

    private int avgEvaluateScore;

    // TODO: wallet, myRooms

    @Builder
    public User(String name, String email, int hostScore, int memberScore, int avgEvaluateScore) {
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
        this.hostScore = hostScore;
        this.memberScore = memberScore;
        this.avgEvaluateScore = avgEvaluateScore;
    }
}
