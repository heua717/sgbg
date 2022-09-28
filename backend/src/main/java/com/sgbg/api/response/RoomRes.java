package com.sgbg.api.response;

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

    @Schema(name = "룸 아이디")
    private Long roomId;

    @Schema(name = "방장 아이디")
    private Long hostId;

    @Schema(name = "방장 닉네임")
    private String hostName;

    @Schema(name = "대분류 카테고리", example = "이색놀거리")
    private String parentCategory;

    @Schema(name = "소분류 카테고리", example = "보드게임")
    private String childCategory;

    @Schema(name = "방 제목", example = "보드게임 고수만")
    private String title;

    @Schema(name = "최대 참여 유저", example = "10")
    private Long maxUser;

    @Schema(name = "최소 참여 유저", example = "2")
    private Long minUser;

    @Schema(name = "모임 참여 비용", example = "50000")
    private Long price;

    @Schema(name = "모임 장소", example = "서울시 멀티캠퍼스")
    private String location;

    @Schema(name = "모임 날짜", example = "2022-12-25T20:00:00")
    private LocalDateTime reservationDate;

    @Schema(name = "모집 마감 날짜", example = "2022-12-20T20:00:00")
    private LocalDateTime endDate;

    @Schema(name = "참여 가능 온도", example = "35.4")
    private Double minMemberScore;

    @Schema(name = "방 정보(설명)", example = "모여서 인생 걸고 보드게임 하실분 모집합니다. 할리갈리 선출입니다.")
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
