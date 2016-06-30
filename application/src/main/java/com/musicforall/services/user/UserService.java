package com.musicforall.services.user;

import com.musicforall.model.User;

import java.util.Collection;

/**
 * Created by Pukho on 16.06.2016.
 */
public interface UserService {
    void save(User user);

    User get(Integer id);

    boolean isUserExist(Integer userId);

    Integer getIdByUsername(String username);

    void delete(Integer userId);

    boolean isUserExist(String username);

    User getByUsername(String username);

    Collection<User> findAll();
}
