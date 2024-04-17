package com.revature.repository;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.utilities.ConnectionUtil;

import java.sql.*;

public class UserDao {
    
    public User getUserByUsername(String username){
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from users where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            User possibleUser = new User();
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int retrievedId = rs.getInt("id");
                String retrievedUsername = rs.getString("username");
                String retrievedPassword = rs.getString("password");
                possibleUser.setId(retrievedId);
                possibleUser.setUsername(retrievedUsername);
                possibleUser.setPassword(retrievedPassword);
            }
            return possibleUser;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public User createUser(UsernamePasswordAuthentication registerRequest) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "insert into users (username, password) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,registerRequest.getUsername());
            ps.setString(2,registerRequest.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            User newUser = new User();
            newUser.setUsername(registerRequest.getUsername());
            newUser.setPassword(registerRequest.getPassword());
            if(rs.next()){
                newUser.setId(rs.getInt(1));
            }
            return newUser;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
