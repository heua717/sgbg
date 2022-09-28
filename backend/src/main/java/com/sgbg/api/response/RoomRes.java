package com.sgbg.api.response;

import com.sgbg.domain.Room;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


@ApiModel("RoomResponse")
@Setter
@NoArgsConstructor
@Getter
@ToString
public class RoomRes extends BaseResponseBody{

    @ApiModelProperty(name = "Room Id")
    private Long roomId;

    @ApiModelProperty(name = "Host Id")
    private Long hostId;

    @ApiModelProperty(name = "Host Name")
    private String hostName;

    @ApiModelProperty(name = "Parent Category")
    private String parentCategory;

    @ApiModelProperty(name = "Child Category")
    private String childCategory;

    @ApiModelProperty(name = "Room Title")
    private String title;

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

    @ApiModelProperty(name = "Image URL")
    private String imageUrl;

    @ApiModelProperty(name = "Min Member Score")
    private Double minMemberScore;

    @ApiModelProperty(name = "Description")
    private String description;


    public static RoomRes getRoomRes(Room room){
        RoomRes roomRes = new RoomRes();
        roomRes.setRoomId(room.getRoomId());
        roomRes.setTitle(room.getTitle());
        roomRes.setHostName(room.getHostName());
        roomRes.setParentCategory(room.getParentCategory());
        roomRes.setChildCategory(room.getChildCategory());
        roomRes.setMaxUser(room.getMaxUser());
        roomRes.setMinUser(room.getMinUser());
        roomRes.setPrice(room.getPrice());
        roomRes.setLocation(room.getLocation());
        roomRes.setReservationDate(room.getReservationDate());
        roomRes.setEndDate(room.getEndDate());
        roomRes.setImageUrl(room.getImageUrl());
        roomRes.setMinMemberScore(room.getMinMemberScore());
        roomRes.setDescription(room.getDescription());
        return roomRes;
    }


    public static RoomRes roomResEntity(Integer status, String Message, RoomRes roomRes){
        roomRes.setStatusCode(status);
        roomRes.setMessage(Message);

        return roomRes;
    }

    public static List<RoomRes> roomListResEntity(Integer status, String Message, List<RoomRes> roomListRes){

        for(int i = 0 ; i<roomListRes.size(); i++){
            roomListRes.get(i).setStatusCode(status);
            roomListRes.get(i).setMessage(Message);

        }
        return roomListRes;
    }


}
