package com.aghanim.service.abstraction;

import com.aghanim.model.User;

import java.util.List;

public interface UserService {
    User saveUser (User user);

    User getUserById(long id);

    void deleteUser(long id);

    List<User> getUsers();
}
