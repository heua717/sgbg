package com.sgbg.service;

import com.sgbg.api.request.RoomReq;
import com.sgbg.api.response.RoomRes;
import com.sgbg.domain.Room;
import com.sgbg.domain.User;
import com.sgbg.repository.RoomRepository;
import com.sgbg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class RoomService {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional()
    public void createRoom(RoomReq roomReq) {
        Room room;
//        User user = userRepository.findById(userId).orElse(null);
//        if(user==null){
//            throw NullPointerException;
//        }
//        room.builder().hostId(user.getUserId);
        room = roomReq.toEntity(roomReq);
//        room.builder().hostId(user.getUserId())
//                        .hostName(user.getName()).build();
        roomRepository.save(room);
    }

    @Transactional()
    public List<RoomRes> getRoomList(Pageable pageable){
        List<Room> roomList = roomRepository.findAllBy(pageable);
        List<RoomRes> roomResList = new ArrayList<>();
        for(Room room : roomList){
            roomResList.add(RoomRes.getRoomRes(room));
        }

        return roomResList;
    }

    public RoomRes getRoom(Long roomId) {
        Room room = roomRepository.findByRoomId(roomId).orElse(null);
//        if(room==null){
//            throw NullPointerException("not find room");
//        }
        RoomRes roomRes = RoomRes.getRoomRes(room);
        return roomRes;

    }

    public List<RoomRes> getRoomList(String parentCategory, Pageable pageable) {
        List<Room> roomList = roomRepository.findAllBy(pageable);
        List<RoomRes> roomResList = new ArrayList<>();
        for(Room room : roomList){
            if(room.getParentCategory()==parentCategory){
                roomResList.add(RoomRes.getRoomRes(room));
            }
        }

        return roomResList;
    }
}
