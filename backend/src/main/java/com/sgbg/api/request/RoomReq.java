package com.sgbg.api.request;

import com.sgbg.domain.Room;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@ApiModel("RoomRequest")
@Setter
@Getter
@NoArgsConstructor
public class RoomReq {

    @ApiModelProperty(name = "Room Title")
    private String title;

    @ApiModelProperty(name = "Host Id")
    private Long hostId;

    @ApiModelProperty(name = "Host Name")
    private String hostName;

    @ApiModelProperty(name = "Parent Category")
    private String parentCategory;

    @ApiModelProperty(name = "Child Category")
    private String childCategory;

    @ApiModelProperty(name = "Max User")
    private Long maxUser;

    @ApiModelProperty(name = "Min User")
    private Long minUser;

    @ApiModelProperty(name = "Price")
    private Long price;

    @ApiModelProperty(name = "Location")
    private String location;

    @ApiModelProperty(name = "Reservation Date")
    private LocalDateTime reservationDate;

    @ApiModelProperty(name = "End Date")
    private LocalDateTime endDate;

    @ApiModelProperty(name = "Min Member Score")
    private Double minMemberScore;

    @ApiModelProperty(name = "Image URL")
    private String imageUrl;

    @ApiModelProperty(name = "description")
    private String description;

    public static RoomReq createRoomReq(String title, String parentCategory,
                   String hostName, String childCategory, Long maxUser,
                   Long minUser, Long price, String location,
                   LocalDateTime reservationDate, LocalDateTime endDate,
                   Double minMemberScore, String imageUrl, String description) {
        RoomReq roomReq = new RoomReq();
        roomReq.setTitle(title);
        roomReq.setParentCategory(parentCategory);
        roomReq.setChildCategory(childCategory);
        roomReq.setMaxUser(maxUser);
        roomReq.setMinUser(minUser);
        roomReq.setPrice(price);
        roomReq.setLocation(location);
        roomReq.setReservationDate(reservationDate);
        roomReq.setEndDate(endDate);
        roomReq.setMinMemberScore(minMemberScore);
        roomReq.setImageUrl(imageUrl);
        roomReq.setDescription(description);

        return roomReq;

    }

    public Room toEntity(RoomReq roomReq){

        return Room.builder()
                .title(roomReq.title)
                .parentCategory(roomReq.parentCategory)
                .childCategory(roomReq.childCategory)
                .maxUser(roomReq.maxUser)
                .minUser(roomReq.minUser)
                .price(roomReq.price)
                .location(roomReq.location)
                .reservationDate(roomReq.reservationDate)
                .endDate(roomReq.endDate)
                .minMemberScore(roomReq.minMemberScore)
                .description(roomReq.description)
                .build();
    }
}
