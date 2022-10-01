package com.sgbg.api.response;

import com.sgbg.domain.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoomListRes extends BaseResponseBody {

    @Schema(name = "roomListInfo", description = "방 목록 정보")
    private List<BaseRoomRes> roomListInfo = new ArrayList<>();

    public static RoomListRes of(Integer statusCode, String message, List<Room> roomList) {
        RoomListRes roomListRes = new RoomListRes();
        roomListRes.setStatusCode(statusCode);
        roomListRes.setMessage(message);
        roomListRes.setRoomListInfo(roomList);
        return roomListRes;
    }

    public void setRoomListInfo(List<Room> roomList) {
        for (Room room : roomList) {
            this.roomListInfo.add(BaseRoomRes.of(room));
        }
    }

}
