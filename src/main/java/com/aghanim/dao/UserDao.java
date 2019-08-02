package com.aghanim.dao;

import com.aghanim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User getUserByLogin(String login);
}
