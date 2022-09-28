package com.sgbg.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Room")
@DynamicUpdate
@DynamicInsert
@Getter
@NoArgsConstructor
public class Room {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "parent_category")
    private String parentCategory;

    @Column(name = "child_category")
    private String childCategory;

    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "title")
    private String title;

    @Column(name = "min_user")
    @ColumnDefault(value = "2")
    private Long minUser;

    @Column(name = "max_user")
    @ColumnDefault(value = "2")
    private Long maxUser;

    @Column(name = "price")
    private Long price;

    @Column(name = "location")
    private String location;

    @Column(name = "reservation_date")
    private LocalDateTime reservationDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "min_member_score")
    private Double minMemberScore;

    @Column(name = "description")
    private String description;

    @Builder
    public Room(String parentCategory, String childCategory,
                Long hostId, String hostName, String title, Long minUser, Long maxUser,
                Long price, String location, LocalDateTime reservationDate,
                LocalDateTime endDate, String imageUrl,
                Double minMemberScore, String description) {

        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
        this.hostId = hostId;
        this.hostName = hostName;
        this.title = title;
        this.minUser = minUser;
        this.maxUser = maxUser;
        this.price = price;
        this.location = location;
        this.reservationDate = reservationDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.minMemberScore = minMemberScore;
        this.description = description;
    }
}
