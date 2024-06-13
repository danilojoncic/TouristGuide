package com.example.touristguide.repository.user;

import com.example.touristguide.domain.user.Status;
import com.example.touristguide.domain.user.Tip;
import com.example.touristguide.domain.user.User;
import com.example.touristguide.dto.CreateUserDto;
import com.example.touristguide.dto.UserLoginDto;
import com.example.touristguide.dto.UserTableDto;
import com.example.touristguide.dto.UserUpdateDto;
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            preparedStatement.setString(1, userLoginDto.getEmail());
            preparedStatement.setString(2, userLoginDto.getPassword());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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
    public List<UserTableDto> getAllUsers(int page,int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<UserTableDto> list = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user LIMIT ? OFFSET ?");
            preparedStatement.setInt(1,pageSize);
            preparedStatement.setInt(2,offset);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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



    @Override
    public void deleteUser(int user_id) {
        Connection connection = null;
        PreparedStatement deleteArticleActivityStmt = null;
        PreparedStatement deleteCommentStmt = null;
        PreparedStatement deleteArticleStmt = null;
        PreparedStatement deleteUserStmt = null;

        try {
            connection = this.newConnection();
            connection.setAutoCommit(false);

            deleteArticleActivityStmt = connection.prepareStatement(
                    "DELETE AA FROM article_activity AA " +
                            "JOIN article A ON AA.article_id = A.article_id " +
                            "WHERE A.autor_id = ?"
            );
            deleteArticleActivityStmt.setInt(1, user_id);
            deleteArticleActivityStmt.executeUpdate();

            deleteCommentStmt = connection.prepareStatement(
                    "DELETE C FROM comment C " +
                            "JOIN article A ON C.article_id = A.article_id " +
                            "WHERE A.autor_id = ?"
            );
            deleteCommentStmt.setInt(1, user_id);
            deleteCommentStmt.executeUpdate();

            deleteArticleStmt = connection.prepareStatement(
                    "DELETE FROM article WHERE autor_id = ?"
            );
            deleteArticleStmt.setInt(1, user_id);
            deleteArticleStmt.executeUpdate();

            deleteUserStmt = connection.prepareStatement(
                    "DELETE FROM user WHERE user_id = ?"
            );
            deleteUserStmt.setInt(1, user_id);
            deleteUserStmt.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            this.closeStatement(deleteArticleActivityStmt);
            this.closeStatement(deleteCommentStmt);
            this.closeStatement(deleteArticleStmt);
            this.closeStatement(deleteUserStmt);
            this.closeConnection(connection);
        }
    }



    @Override
    public UserUpdateDto findUserById(int user_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserUpdateDto user = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserUpdateDto();
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setUser_id(resultSet.getInt("user_id"));
                user.setTip(Tip.valueOf(resultSet.getString("tip")));
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
    public void editUser(int user_id,UserUpdateDto userUpdateDto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE user SET firstname = ?, lastname = ?,email = ?, tip = ? WHERE user_id = ?");
            preparedStatement.setString(1, userUpdateDto.getFirstname());
            preparedStatement.setString(2, userUpdateDto.getLastname());
            preparedStatement.setString(3,userUpdateDto.getEmail());
            preparedStatement.setString(4,String.valueOf(userUpdateDto.getTip()));
            preparedStatement.setInt(5,userUpdateDto.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public void changeStatus(int user_id,String status) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE user SET status = ? WHERE user_id = ?");
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2,user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
