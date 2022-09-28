package com.sgbg.api.response;

import com.sgbg.domain.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
public class RoomListRes extends BaseResponseBody {

    @Schema(name = "방 목록")
    private List<RoomRes> roomListRes = new ArrayList<>();

    public static RoomListRes of(Integer statusCode, String message, List<RoomRes> roomList){
        RoomListRes roomListRes = new RoomListRes();
        roomListRes.setStatusCode(statusCode);
        roomListRes.setMessage(message);
        roomListRes.setRoomListRes(roomList);
        return roomListRes;
    }
}
