package com.example.touristguide.repository.user;

import com.example.touristguide.domain.Destination;
import com.example.touristguide.domain.user.Status;
import com.example.touristguide.domain.user.Tip;
import com.example.touristguide.domain.user.User;
import com.example.touristguide.dto.UserLoginDto;
import com.example.touristguide.repository.MDBRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
