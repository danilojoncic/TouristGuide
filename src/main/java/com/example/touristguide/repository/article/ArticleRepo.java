package com.example.touristguide.repository.article;

import com.example.touristguide.dto.CreateArticleDto;
import com.example.touristguide.repository.MDBRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArticleRepo extends MDBRepository implements ArticleRepoInterface {

    @Override
    public void addArticle(CreateArticleDto createArticleDto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            List<Integer> tags = createArticleDto.getTags();
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO article (article_id,title,text,visit_count,autor_id,destination_id)"
                            + "VALUES(NULL,?,?,0,?,?)");
            preparedStatement.setString(1,createArticleDto.getTitle());
            preparedStatement.setString(2,createArticleDto.getText());
            preparedStatement.setInt(3,createArticleDto.getAutor_id());
            preparedStatement.setInt(4,createArticleDto.getDestination_id());
            preparedStatement.executeUpdate();

            generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                int article_id = generatedKeys.getInt(1);
                for (Integer tag : tags) {
                    preparedStatement = connection.prepareStatement("INSERT INTO article_activity (article_id,activity_id)"
                            +"VALUES (?,?)");
                    preparedStatement.setInt(1,article_id);
                    preparedStatement.setInt(2,tag);
                    preparedStatement.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public void deleteArticle(int article_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM article WHERE article_id = ?");
            preparedStatement.setInt(1, article_id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM article_activity WHERE article_id = ?");
            preparedStatement.setInt(1, article_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
