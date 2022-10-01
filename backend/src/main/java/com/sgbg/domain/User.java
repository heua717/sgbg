package com.sgbg.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
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
    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_room",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Room> myRooms = new ArrayList<>();

    @Builder
    public User(String name, String email, int hostScore, int memberScore, int avgEvaluateScore) {
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
        this.hostScore = hostScore;
        this.memberScore = memberScore;
        this.avgEvaluateScore = avgEvaluateScore;
    }

    public void addMyRoom(Room room) {
        myRooms.add(room);
        room.getMembers().add(this);
    }
}
