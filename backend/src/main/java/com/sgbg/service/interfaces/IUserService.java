package com.sgbg.service.interfaces;

import com.sgbg.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface IUserService {

    public User createUser(Map<String, String> userInfo);

    public User findUserById(Long userId);
}
