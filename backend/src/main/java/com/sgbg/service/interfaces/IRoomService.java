package com.sgbg.service.interfaces;

import com.sgbg.api.request.RoomReq;
import com.sgbg.domain.Room;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRoomService {

    @Transactional
    Room createRoom(RoomReq roomReq, Long userId);

    @Transactional
    Room setRoomContactAddress(Room room, String contactAddress);

    @Transactional
    List<Room> getRoomList();

    Room getRoom(Long roomId);

    List<Room> getParentRoomList(String parentCategory);

    List<Room> getChildRoomList(String childCategory);

    List<Room> searchRoom(String keyword);
}
