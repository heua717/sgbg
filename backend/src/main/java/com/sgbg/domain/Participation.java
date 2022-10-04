package com.sgbg.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id")
    private Long id;

    private boolean isParticipate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Builder
    public Participation(Boolean isParticipate, Room room, User user) {
        this.isParticipate = isParticipate;
        this.room = room;
        this.user = user;
    }

    public void addMember(User user, Room room) {
        user.getMyRooms().add(this);
        room.getMembers().add(this);
    }

    public void deleteMember() {
        this.isParticipate = false;
        room.getMembers().remove(this);
    }
}
