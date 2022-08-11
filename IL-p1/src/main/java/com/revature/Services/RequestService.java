package com.revature.Services;

import com.revature.DAOS.RequestDAOS;

import com.revature.pojos.Requests;

import java.util.List;

public class RequestService {
    private RequestDAOS dao;

    public RequestService() {
        this.dao = new RequestDAOS();
    }

    public void saveRequest(Requests request) {
        dao.create(request);
    }

    public Requests getRequest(int id) {
        return dao.read(id);
    }

    public List<Requests> getAllRequests() {
        return dao.readAll();
    }

    public void updateRequest(Requests request) {
        dao.update(request);
    }

    public void deleteRequest(int id) {
        dao.delete(id);

    }
}



