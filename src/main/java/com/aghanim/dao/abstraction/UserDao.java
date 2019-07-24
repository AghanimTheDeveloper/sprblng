package com.aghanim.dao.abstraction;

import com.aghanim.model.User;

import java.util.List;

public interface UserDao {
    User saveUser(User user);

    User getUserById(long id);

    void deleteUser(long id);

    List<User> getUsers();
}
