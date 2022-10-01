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

        // TODO: 방장(만든 사람)도 member로 추가
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
    public List<Room> getRoomList(){
        return roomRepository.findAll();
    }

    public Room getRoom(Long roomId) {
        return roomRepository.findByRoomId(roomId).orElse(null);
//        if(room==null){
//            throw NullPointerException("not find room");
//        }
    }

    public List<Room> getParentRoomList(String parentCategory) {
//        List<RoomRes> roomResList = new ArrayList<>();
//        for(Room room : roomList){
//            if(room.getParentCategory().equals(parentCategory)){
//                roomResList.add(RoomRes.getRoomRes(room));
//            }
//        }
        return roomRepository.findAllByParentCategory(parentCategory);
    }

    public List<Room> getChildRoomList(String childCategory) {
//        List<RoomRes> roomResList = new ArrayList<>();
//        for(Room room : roomList){
//            if(room.getChildCategory().equals(childCategory)){
//                roomResList.add(RoomRes.getRoomRes(room));
//            }
//        }
        return roomRepository.findAllByChildCategory(childCategory);
    }
}
