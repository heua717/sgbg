package com.sgbg.service.interfaces;

import com.sgbg.domain.Room;
import com.sgbg.domain.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    public User createUser(Map<String, String> userInfo);

    public User getUserById(Long userId);
    List<Room> getMyRooms(Long userId, Boolean isHost);
}
