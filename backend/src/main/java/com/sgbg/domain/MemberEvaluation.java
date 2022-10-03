package com.sgbg.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class MemberEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_evaluation_id")
    private Long id;

    private int score;

    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluator_id", referencedColumnName = "user_id")
    private User evaluator; // 평가하는 사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Builder
    public MemberEvaluation(int score, User evaluator, Long transactionId, User user, Room room) {
        this.score = score;
        this.transactionId = transactionId;
        this.evaluator = evaluator;
        this.user = user;
        this.room = room;
    }

    public void addMemberEvaluation(Room room, User evaluator, User user) {
        room.getMemberEvaluations().add(this);
        evaluator.getMyMemberEvaluations().add(this);
        user.getMyMemberEvaluationResults().add(this);
    }
}
