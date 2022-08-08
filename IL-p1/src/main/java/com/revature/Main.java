package com.revature;

import com.revature.pojos.User;

import com.revature.DAOS.UserDAOS;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){

        Connection connection = ConnectionManager.getConnection();
        User newUser = new User("jeff", "Lash", "ilash99@m", "mynameisjeff", false);

        UserDAOS dao = new UserDAOS(ConnectionManager.getConnection());
        dao.create(newUser);


    }



}
