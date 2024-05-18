package com.example.touristguide.repository.user;

import com.example.touristguide.domain.user.Status;
import com.example.touristguide.domain.user.Tip;
import com.example.touristguide.domain.user.User;
import com.example.touristguide.dto.CreateUserDto;
import com.example.touristguide.dto.UserLoginDto;
import com.example.touristguide.dto.UserTableDto;
import com.example.touristguide.repository.MDBRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo extends MDBRepository implements UserRepoInterface {

    /**
     * Uzima userLoginDto i na osnovu njega mi vraca korisnika kome pripadaju dati podaci
     * @param userLoginDto email i sifra moguceg korisinika iz baze
     * @return vraca objekat korisnika ako se on stvarno nalazi u bazi, u suprontnom ce se vratiti
     * null sto znaci da su podaci iz userLoginDto-a losi
     */
    @Override
    public User findUserByLogin(UserLoginDto userLoginDto) {
        System.out.println("USER REPO CALLED");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            preparedStatement.setString(1, userLoginDto.getEmail());
            preparedStatement.setString(2, userLoginDto.getPassword());
            System.out.println("Prepared statement " + preparedStatement.toString());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("REPO LAYER MAKING USER");
                user = new User();
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setUser_id(resultSet.getInt("user_id"));
                user.setTip(Tip.valueOf(resultSet.getString("tip")));
                user.setStatus(Status.valueOf(resultSet.getString("status")));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return user;
    }

    @Override
    public List<UserTableDto> getAllUsers() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<UserTableDto> list = new ArrayList<>();
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserTableDto userTableDto = new UserTableDto();
                userTableDto.setFirstname(resultSet.getString("firstname"));
                userTableDto.setLastname(resultSet.getString("lastname"));
                userTableDto.setEmail(resultSet.getString("email"));
                userTableDto.setUser_id(resultSet.getInt("user_id"));
                userTableDto.setTip(Tip.valueOf(resultSet.getString("tip")));
                userTableDto.setStatus(Status.valueOf(resultSet.getString("status")));
                list.add(userTableDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return list;
    }

    @Override
    public void addUser(CreateUserDto createUserDto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO user (user_id, firstname,lastname,email,tip,status,password) "
                    + "VALUES(NULL,?,?,?,?,?,?)");
            preparedStatement.setString(1, createUserDto.getFirstname());
            preparedStatement.setString(2, createUserDto.getLastname());
            preparedStatement.setString(3, createUserDto.getEmail());
            preparedStatement.setString(4,String.valueOf(createUserDto.getTip()));
            preparedStatement.setString(5,"active");
            preparedStatement.setString(6, createUserDto.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
