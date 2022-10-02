package com.sgbg.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class HostEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "host_evaluation_id")
    private Long id;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 평가 받는 사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Builder
    public HostEvaluation(int score, User user, Room room) {
        this.score = score;
        this.user = user;
        this.room = room;
    }

    public void setUser(User user) {
        this.user = user;
        user.getMyHostEvaluationResults().add(this);
    }

    public void setRoom(Room room) {
        this.room = room;
        room.getHostEvaluations().add(this);
    }
}
