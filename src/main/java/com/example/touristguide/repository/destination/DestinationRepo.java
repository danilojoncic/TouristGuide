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


    @Override
    public void deleteDestination(int destination_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM destination WHERE destination_id = ?");
            preparedStatement.setInt(1, destination_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
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
    public List<Destination> getAllDestinations() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Destination> destinations = new ArrayList<>();
        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM destination");
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
}
