package com.sgbg.service;

import com.sgbg.api.request.RoomReq;
import com.sgbg.api.response.RoomRes;
import com.sgbg.domain.Location;
import com.sgbg.domain.Room;
import com.sgbg.domain.User;
import com.sgbg.repository.LocationRepository;
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
    LocationRepository locationRepository;
    @Autowired
    UserRepository userRepository;


    @Transactional()
    public void createRoom(RoomReq roomReq) {
        Room room = roomReq.toEntity(roomReq);
        Location location = roomReq.getLocation();
//        User user = userRepository.findById(userId).orElse(null);
//        if(user==null){
//            throw NullPointerException;
//        }
//        room.builder().hostId(user.getUserId);
//        room.builder().hostId(user.getUserId())
//                        .hostName(user.getName()).build();
        locationRepository.save(location);
        roomRepository.save(room);
    }

    @Transactional()
    public List<RoomRes> getRoomList(){
        List<Room> roomList = roomRepository.findAll();
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

    public List<RoomRes> getParentRoomList(String parentCategory) {
        List<Room> roomList = roomRepository.findAllByParentCategory(parentCategory);
        List<RoomRes> roomResList = new ArrayList<>();
        for(Room room : roomList){
            if(room.getParentCategory().equals(parentCategory)){
                roomResList.add(RoomRes.getRoomRes(room));
            }
        }

        return roomResList;
    }

    public List<RoomRes> getChildRoomList(String childCategory) {
        List<RoomRes> roomResList = new ArrayList<>();
        List<Room> roomList = roomRepository.findAllByChildCategory(childCategory);
        for(Room room : roomList){
            if(room.getChildCategory().equals(childCategory)){
                roomResList.add(RoomRes.getRoomRes(room));
            }
        }

        return roomResList;

    }
}
