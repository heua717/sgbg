package com.sgbg.api.response;

import com.sgbg.domain.Location;
import com.sgbg.domain.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;



@Setter
@NoArgsConstructor
@Getter
@ToString
public class RoomRes extends BaseResponseBody{

    @Schema(name = "roomId")
    private Long roomId;

    @Schema(name = "hostId")
    private Long hostId;

    @Schema(name = "hostName")
    private String hostName;

    @Schema(name = "parentCategory", example = "이색놀거리")
    private String parentCategory;

    @Schema(name = "childCategory", example = "보드게임")
    private String childCategory;

    @Schema(name = "title", example = "보드게임 고수만")
    private String title;

    @Schema(name = "maxUser", example = "10")
    private Long maxUser;

    @Schema(name = "minUser", example = "2")
    private Long minUser;

    @Schema(name = "price", example = "50000")
    private Long price;

    @Schema(name = "location", example = "서울시 멀티캠퍼스")
    private Location location;

    @Schema(name = "reservationDate", example = "2022-12-25T20:00:00")
    private LocalDateTime reservationDate;

    @Schema(name = "endDate", example = "2022-12-20T20:00:00")
    private LocalDateTime endDate;

    @Schema(name = "minMemberScore", example = "35.4")
    private Double minMemberScore;

    @Schema(name = "description", example = "모여서 인생 걸고 보드게임 하실분 모집합니다. 할리갈리 선출입니다.")
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
