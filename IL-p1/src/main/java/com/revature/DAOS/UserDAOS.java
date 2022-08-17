package com.revature.DAOS;
import com.revature.pojos.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import com.revature.Services.DatasourceService;

public class UserDAOS implements DAOScrud<User> {

    Connection connection; //set the connection for this class to use


    public UserDAOS(){
        this.connection = DatasourceService.getConnection() ;
    }

    @Override
    public void create(User user) {
        try {
            String sql = "INSERT INTO user_table (first_name, last_name,  email_add, use_pass) VALUES (?, ?, ?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            System.out.println("creating user");
            pstmt.executeUpdate();


        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public User read(int uid) {
        User user = new User();
        try {
            String sql = "SELECT * FROM user_table WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,uid );
            ResultSet results = pstmt.executeQuery();


            if(results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setEmail(results.getString("email_add"));
                user.setPassword(results.getString("use_pass"));
                user.setAdmin(results.getBoolean("is_Admin"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }
    public User logIn(String email, String password) {
        User user = new User();
        try {
            System.out.println("loggin in");
            String sql = "SELECT * FROM user_table WHERE email_add = ? AND use_pass = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,email);
            pstmt.setString(2,password);
            ResultSet results = pstmt.executeQuery();


            if(results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setEmail(results.getString("email_add"));
                user.setPassword(results.getString("use_pass"));
                user.setAdmin(results.getBoolean("is_Admin"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

    @Override
    public List<User> readAll() {
        List<User> userList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM user_table ORDER BY user_id";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet results = pstmt.executeQuery();



            while(results.next()) {
                User user = new User();
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setEmail(results.getString("email_add"));
                user.setPassword(results.getString("use_pass"));
                user.setAdmin(results.getBoolean("is_Admin"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }


    @Override
    public void update(User user) {
        System.out.println("updating user");
        try {

            String sql = "UPDATE user_table SET first_name = ?, last_name = ?, email_add = ?, use_pass = ?, is_admin = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setBoolean(5, user.isAdmin());
            pstmt.setInt(6, user.getUserId());

            pstmt.executeUpdate();
            System.out.println("updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(int id) {
        try{
            String anotherOne = "DELETE FROM reimburse_req WHERE user_id = ?";

            PreparedStatement psmt = connection.prepareStatement(anotherOne);
            psmt.setInt(1, id);

            psmt.executeUpdate();
            //gotta delete the requests first to be able to delete a user thanks to primary key magic
            String sql = "DELETE FROM user_table WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();




        }catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("deleted");
    }


}
