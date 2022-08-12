package com.revature.servlets;

import javax.servlet.http.HttpServlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.User;
import com.revature.Services.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {

    UserService service;
    ObjectMapper mapper;


    @Override
    public void init() throws ServletException {
        System.out.println("User servlet initializing...");
        this.service = new UserService();
        this.mapper = new ObjectMapper();
        System.out.println("User servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("user-id");
        String email = req.getParameter("email");
        String password = req.getParameter("passw");
        if(param == null && email == null && password ==null) {
            //Return all
            List<User> userList = service.getAllUsers();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else if (param != null){
            //return the one the request wants
            Integer userId = Integer.parseInt(req.getParameter("user-id"));

            User user = service.getUser(userId);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        }else {
        //get user with credintials

            User user = service.checkUser(email, password);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder bob = new StringBuilder(); //bob da string builder
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            bob.append(buffer.readLine());
        }
        String json = bob.toString();
        System.out.println("posting");

        User newUser = mapper.readValue(json, User.class);
        service.saveUser(newUser);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder bob = new StringBuilder(); //bob da string builder
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            bob.append(buffer.readLine());
        }
        String json = bob.toString();


        User newUser = mapper.readValue(json, User.class);
        service.updateUser(newUser);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("user-id");

        if(param == null) {
            //Return all

            resp.getWriter().println("Unable to delete user as the User ID does not exist.");
        } else {
            //return the one the request wants
            Integer userId = Integer.parseInt(req.getParameter("user-id"));

             service.deleteUser(userId);

            resp.getWriter().println("Deleted successfully!");
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}

