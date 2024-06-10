package com.example.touristguide.repository.activity;

import com.example.touristguide.domain.Activity;
import com.example.touristguide.domain.Destination;
import com.example.touristguide.dto.CreateActivityDTO;
import com.example.touristguide.repository.MDBRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepo extends MDBRepository implements ActivityRepoInteface {
    @Override
    public void addActivity(CreateActivityDTO createActivityDTO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO activity (activity_id,tag) VALUES( NULL,?)");
            preparedStatement.setString(1, createActivityDTO.getTag());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public Activity getActivity(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Activity activity = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM activity WHERE activity_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                activity = new Activity();
                activity.setActivity_id(resultSet.getInt("activity_id"));
                activity.setTag(resultSet.getString("tag"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return activity;
    }

    @Override
    public List<Activity> getAllActivities(int page, int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Activity> activities = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM activity LIMIT ? OFFSET ?");
            preparedStatement.setInt(1,pageSize);
            preparedStatement.setInt(2,offset);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Activity activity = new Activity();
                activity.setActivity_id(resultSet.getInt("activity_id"));
                activity.setTag(resultSet.getString("tag"));
                activities.add(activity);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return activities;
    }

    @Override
    public void editActivity(CreateActivityDTO createActivityDTO, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE activity SET tag = ? WHERE activity_id = ?");
            preparedStatement.setString(1, createActivityDTO.getTag());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public void deleteActivity(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM article_activity WHERE activity_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();


            preparedStatement = connection.prepareStatement("DELETE FROM activity WHERE activity_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
