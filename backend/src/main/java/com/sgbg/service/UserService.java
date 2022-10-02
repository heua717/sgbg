package com.sgbg.service;

import com.sgbg.common.exception.NotFoundException;
import com.sgbg.domain.Room;
import com.sgbg.repository.ParticipationRepository;
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
    private ParticipationRepository participationRepository;

    @Override
    @Transactional
    public User createUser(Map<String, String> userInfo) {
        User user = User.builder()
                .name(userInfo.get("name"))
                .email(userInfo.get("email"))
                .build();

        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findUserById(userId).orElse(null);
    }

    @Override
    public List<Room> getMyRooms(Long userId, Boolean isHost) {
        User user = findUserById(userId);
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
}
