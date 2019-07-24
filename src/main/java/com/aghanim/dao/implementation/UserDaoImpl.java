package com.aghanim.dao.implementation;

import com.aghanim.dao.abstraction.UserDao;
import com.aghanim.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public User saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        return user;
    }

    @Override
    public User getUserById(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        sessionFactory.getCurrentSession().delete(getUserById(id));
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("from User").list();
    }
}
