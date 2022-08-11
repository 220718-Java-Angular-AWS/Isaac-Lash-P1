package com.revature.Services;


import com.revature.DAOS.UserDAOS;
import com.revature.pojos.User;

import java.util.List;


public class UserService  {
    private UserDAOS dao;

    public UserService() {
        this.dao = new UserDAOS();
    }

    public void saveUser(User user) {
        dao.create(user);
    }

    public User getUser(int id) {
        return dao.read(id);
    }

    public User checkUser(String email, String password) {
        return dao.logIn(email, password);
    }

    public List<User> getAllUsers() {
        return dao.readAll();
    }

    public void updateUser(User user) {
        dao.update(user);
    }

    public void deleteUser(int id) {
        dao.delete(id);

    }
}

