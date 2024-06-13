package com.example.touristguide.repository.destination;

import com.example.touristguide.domain.Destination;
import com.example.touristguide.dto.CreateDestinationDTO;
import com.example.touristguide.repository.MDBRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DestinationRepo extends MDBRepository implements DestinationRepoInterface{
    @Override
    public void addDestination(CreateDestinationDTO createDestinationDTO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO destination (destination_id, name,description) VALUES( NULL,?, ?)");
            preparedStatement.setString(1, createDestinationDTO.getName());
            preparedStatement.setString(2, createDestinationDTO.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public void editDestination(CreateDestinationDTO destination,int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE destination SET name = ?, description = ? WHERE destination_id = ?");
            preparedStatement.setString(1, destination.getName());
            preparedStatement.setString(2, destination.getDescription());
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }


    //ovaj pristup sa "transakcijom" sam koristio za brisanje destinacije i brisanje usera
    @Override
    public void deleteDestination(int destination_id) {
        Connection connection = null;
        PreparedStatement deleteArticleActivityStmt = null;
        PreparedStatement deleteCommentStmt = null;
        PreparedStatement deleteArticleStmt = null;
        PreparedStatement deleteDestinationStmt = null;

        try {
            connection = this.newConnection();
            connection.setAutoCommit(false);

            deleteArticleActivityStmt = connection.prepareStatement(
                    "DELETE AA FROM article_activity AA " +
                            "JOIN article A ON AA.article_id = A.article_id " +
                            "WHERE A.destination_id = ?"
            );
            deleteArticleActivityStmt.setInt(1, destination_id);
            deleteArticleActivityStmt.executeUpdate();

            deleteCommentStmt = connection.prepareStatement(
                    "DELETE C FROM comment C " +
                            "JOIN article A ON C.article_id = A.article_id " +
                            "WHERE A.destination_id = ?"
            );
            deleteCommentStmt.setInt(1, destination_id);
            deleteCommentStmt.executeUpdate();

            deleteArticleStmt = connection.prepareStatement(
                    "DELETE FROM article WHERE destination_id = ?"
            );
            deleteArticleStmt.setInt(1, destination_id);
            deleteArticleStmt.executeUpdate();

            deleteDestinationStmt = connection.prepareStatement(
                    "DELETE FROM destination WHERE destination_id = ?"
            );
            deleteDestinationStmt.setInt(1, destination_id);
            deleteDestinationStmt.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction on error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            this.closeStatement(deleteArticleActivityStmt);
            this.closeStatement(deleteCommentStmt);
            this.closeStatement(deleteArticleStmt);
            this.closeStatement(deleteDestinationStmt);
            this.closeConnection(connection);
        }
    }




    @Override
    public Destination getDestination(int destination_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Destination destination = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM destination WHERE destination_id = ?");
            preparedStatement.setInt(1, destination_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                destination = new Destination();
                destination.setDestination_id(resultSet.getInt("destination_id"));
                destination.setName(resultSet.getString("name"));
                destination.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return destination;
    }


    @Override
    public List<Destination> getAllDestinations(int page, int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Destination> destinations = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM destination LIMIT ? OFFSET ?");
            preparedStatement.setInt(1,pageSize);
            preparedStatement.setInt(2,offset);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Destination destination = new Destination();
                destination.setDestination_id(resultSet.getInt("destination_id"));
                destination.setName(resultSet.getString("name"));
                destination.setDescription(resultSet.getString("description"));
                destinations.add(destination);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return destinations;
    }

    @Override
    public Destination findByName(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Destination destination = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM destination WHERE name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                destination = new Destination();
                destination.setDestination_id(resultSet.getInt("destination_id"));
                destination.setName(resultSet.getString("name"));
                destination.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return destination;
    }
}
