package com.sgbg.service;

import com.sgbg.repository.UserRepository;
import com.sgbg.service.interfaces.IUserService;
import com.sgbg.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(Map<String, String> userInfo) {
        User user = User.builder()
                .name(userInfo.get("name"))
                .name(userInfo.get("email"))
                .build();

        return userRepository.save(user);
    }
}
