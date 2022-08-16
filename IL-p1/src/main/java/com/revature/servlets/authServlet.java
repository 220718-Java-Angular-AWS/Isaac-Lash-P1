package com.revature.servlets;


import javax.servlet.http.HttpServlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.DAOS.UserDAOS;
import com.revature.pojos.User;
import com.revature.Services.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
public class authServlet extends HttpServlet{
    ObjectMapper mapper;
    UserService service;


    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        this.mapper = new ObjectMapper();
        this.service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String email = req.getParameter("email");
        String password = req.getParameter("passw");

            //return the one the request wants




            //get user with credintials

            User user = service.authenticate(email, password);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);


        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
