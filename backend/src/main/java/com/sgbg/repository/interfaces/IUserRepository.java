package com.sgbg.repository.interfaces;

import com.sgbg.domain.User;

import java.util.List;

public interface IUserRepository {
    List<User> list();
    User get(long id);
    User get(String email);

    long create(User user);
    int update(User user);
    int delete(long id);
}
