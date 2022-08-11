package com.revature.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Services.RequestService;
import com.revature.pojos.Requests;
import com.revature.pojos.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class RequestServlet extends HttpServlet{

    RequestService service;
    ObjectMapper mapper;


    @Override
    public void init() throws ServletException {
        System.out.println("Request servlet initializing...");
        this.service = new RequestService();
        this.mapper = new ObjectMapper();
        System.out.println("Request servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("req-id");

        if(param == null) {
            //Return all
            List<Requests> userList = service.getAllRequests();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else {
            //return the one the request wants
            Integer reqId = Integer.parseInt(req.getParameter("req-id"));

            Requests request = service.getRequest(reqId);
            String json = mapper.writeValueAsString(request);
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


        Requests newRequest = mapper.readValue(json, Requests.class);
        service.saveRequest(newRequest);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder bob = new StringBuilder(); //bob da string builder
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            bob.append(buffer.readLine());
        }
        String json = bob.toString();


        Requests newRequest = mapper.readValue(json, Requests.class);
        service.updateRequest(newRequest);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("req-id");

        if(param == null) {
            //Return all

            resp.getWriter().println("Unable to delete user as the Request ID does not exist.");
        } else {
            //return the one the request wants
            Integer reqId = Integer.parseInt(req.getParameter("req-id"));

            service.deleteRequest(reqId);

            resp.getWriter().println("Deleted successfully!");
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}
