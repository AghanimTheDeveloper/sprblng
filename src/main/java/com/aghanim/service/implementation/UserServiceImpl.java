package com.aghanim.service.implementation;

import com.aghanim.dao.RoleDao;
import com.aghanim.dao.UserDao;
import com.aghanim.model.Role;
import com.aghanim.model.User;
import com.aghanim.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    @Transactional
    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(2L));
        user.setRoles(roles);
        userDao.save(user);
        return user;
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return userDao.getOne(id);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.delete(userDao.getOne(id));
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDao.findAll();
    }
}
