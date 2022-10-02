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

    @Column(name = "is_success")
    private Boolean isSuccess;

    @Column(name = "transaction_id")
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
    public MemberEvaluation(Boolean isSuccess, User evaluator, Long transactionId, User user, Room room) {
        this.isSuccess = isSuccess;
        this.transactionId = transactionId;
        this.evaluator = evaluator;
        this.user = user;
        this.room = room;
    }

    public void setEvaluator(User user) {
        this.evaluator = user;
        user.getMyMemberEvaluations().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getMyMemberEvaluations().add(this);
    }

    public void setRoom(Room room) {
        this.room = room;
        room.getMemberEvaluations().add(this);
    }
}
