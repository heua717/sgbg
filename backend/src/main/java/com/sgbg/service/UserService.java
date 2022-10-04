package com.sgbg.service;

import com.sgbg.common.exception.NotFoundException;
import com.sgbg.domain.Participation;
import com.sgbg.domain.Room;
import com.sgbg.repository.ParticipationRepository;
import com.sgbg.repository.RoomRepository;
import com.sgbg.repository.UserRepository;
import com.sgbg.service.interfaces.IUserService;
import com.sgbg.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Override
    @Transactional
    public User createUser(Map<String, String> userInfo) {
        User user = User.builder()
                .name(userInfo.get("name"))
                .email(userInfo.get("email"))
                .memberScore(50)
                .hostScore(50)
                .avgEvaluateScore(0)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findUserById(userId).orElse(null);
    }

    @Override
    @Transactional
    public Participation addMyRoom(Long userId, Long roomId) {
        User user = getUserById(userId);
        if (user == null) {
            throw new NotFoundException("회원 정보를 찾을 수 없습니다.");
        }
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            throw new NotFoundException("Room Not Found");
        }

        Participation participation = participationRepository.findParticipationByUser(user).orElse(null);
        if (participation == null) {
            participation = Participation.builder()
                    .isParticipate(true)
                    .user(user)
                    .room(room)
                    .build();
        } else {
            participation.setParticipate(true);
        }

        participation.addMember(user, room);
        return participationRepository.save(participation);
    }

    @Override
    public List<Room> getMyRooms(Long userId, Boolean isHost) {
        User user = getUserById(userId);
        if (user == null) {
            throw new NotFoundException("회원 정보를 찾을 수 없습니다.");
        }

        List<Room> myRoomsByUserId = participationRepository.findMyRoomsByUserId(userId);
        if (isHost) { // host = true
            return myRoomsByUserId
                    .stream().filter(room -> room.getHostId().equals(userId))
                    .collect(Collectors.toList());
        }
        return myRoomsByUserId
                .stream().filter(room -> !room.getHostId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Room deleteMyRoom(Long userId, Long roomId) {
        User user = getUserById(userId);
        if (user == null) {
            throw new NotFoundException("회원 정보를 찾을 수 없습니다.");
        }

        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            throw new NotFoundException("Room Not Found");
        }
        Participation participation = participationRepository.findParticipationByUserAndRoom(user, room).orElse(null);
        if (participation != null) {
            participation.deleteMember();
        }

        return room;
    }
}
