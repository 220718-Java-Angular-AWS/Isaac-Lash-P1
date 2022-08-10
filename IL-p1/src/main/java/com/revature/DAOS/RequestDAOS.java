package com.revature.DAOS;
import com.revature.Services.DatasourceService;
import com.revature.pojos.Requests;
import com.revature.pojos.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;





public class RequestDAOS implements DAOScrud<Requests> {

    Connection connection; //set the connection for this class to use
    public RequestDAOS() {
        this.connection = DatasourceService.getConnection();
    }
    public RequestDAOS(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void create(Requests requests) {

        try{
            String sql = "INSERT INTO reimburse_req (user_id, req_comment, req_reason, req_amount, isApproved) VALUES( ?, ?, ?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, requests.getUserId());
            pstmt.setString(2, requests.getComment());
            pstmt.setString(3, requests.getReason());
            pstmt.setDouble(4, requests.getAmount());
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public Requests read(int rid) {
        Requests req = new Requests();
        try {
            String sql = "SELECT * FROM reimburse_req WHERE req_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,rid );
            ResultSet results = pstmt.executeQuery();


            if(results.next()) {
                req.setReqId(results.getInt("req_id"));
                req.setUserId(results.getInt("user_id"));
                req.setComment(results.getString("req_comment"));
                req.setReason(results.getString("req_reason"));
                req.setAmount(results.getDouble("req_amount"));
                req.setApproved(results.getBoolean("approved"));
                req.setDate(results.getString("date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return req;

    }

    @Override
    public List<Requests> readAll() {
        List<Requests> reqList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM reimburse_req";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();



            while(results.next()) {
                Requests req = new Requests();
                req.setReqId(results.getInt("req_id"));
                req.setUserId(results.getInt("user_id"));
                req.setComment(results.getString("req_comment"));
                req.setReason(results.getString("req_reason"));
                req.setAmount(results.getDouble("req_amount"));
                req.setApproved(results.getBoolean("approved"));
                req.setDate(results.getString("date"));
                reqList.add(req);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reqList;
    }

    @Override
    public void update(Requests requests) {
        try {
            String sql = "UPDATE reimburse_req SET user_id = ?, req_comment = ?, req_reason = ?, req_amount = ?, approved = ?, WHERE req_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, requests.getUserId());
            pstmt.setString(2, requests.getComment());
            pstmt.setString(3, requests.getReason());
            pstmt.setDouble(4, requests.getAmount());
            pstmt.setBoolean(5, requests.isApproved());
            pstmt.setInt(6, requests.getReqId());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int reqID) {

        try{
            String sql = "DELETE FROM reimburse_req WHERE req_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, reqID);

            pstmt.executeUpdate();





        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

