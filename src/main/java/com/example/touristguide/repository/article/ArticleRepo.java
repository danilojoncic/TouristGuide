package com.example.touristguide.repository.article;

import com.example.touristguide.dto.ArticlePresentationDto;
import com.example.touristguide.dto.CreateArticleDto;
import com.example.touristguide.repository.MDBRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public ArticlePresentationDto getOneArticle(int article_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArticlePresentationDto article = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT a.article_id, a.title, a.text, a.visit_count, a.autor_id," +
                            " u.firstname, u.lastname, a.destination_id," +
                            " d.name AS destination_name " +
                            "FROM article a " +
                            "INNER JOIN user u " +
                            "ON a.autor_id = u.user_id " +
                            "INNER JOIN destination d " +
                            "ON a.destination_id = d.destination_id WHERE a.article_id = ?;");
            preparedStatement.setInt(1, article_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                article = new ArticlePresentationDto();
                article.setArticle_id(resultSet.getInt("article_id"));
                article.setTitle(resultSet.getString("title"));
                article.setText(resultSet.getString("text"));
                article.setVisit_count(resultSet.getInt("visit_count"));
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                StringBuilder sb = new StringBuilder("");
                sb.append(firstname + " ");
                sb.append(lastname);
                article.setAuthor_name(sb.toString());
                article.setAutor_id(resultSet.getString("autor_id"));
                article.setDestination_id(resultSet.getInt("destination_id"));
                article.setDestination_name(resultSet.getString("destination_name"));

            }

            preparedStatement = connection.prepareStatement(
                    "SELECT article_activity.article_id," +
                            " activity.tag," +
                            "activity.activity_id" +
                            " FROM article_activity " +
                            "INNER JOIN activity " +
                            "ON article_activity.activity_id = activity.activity_id " +
                            " WHERE article_activity.article_id = ?"
            );
            preparedStatement.setInt(1,article_id);
            resultSet = preparedStatement.executeQuery();
            Map<String,Integer> tags = new HashMap<>();
            while(resultSet.next()){
                tags.put(resultSet.getString("tag"),resultSet.getInt("activity_id"));
            }
            article.setTags(tags);

            int newVisitCount = article.getVisit_count() + 1;
            article.setVisit_count(newVisitCount);

            // Update the visit count in the database
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE article SET visit_count = ? WHERE article_id = ?");
            updateStatement.setInt(1, newVisitCount);
            updateStatement.setInt(2, article_id);
            updateStatement.executeUpdate();
            updateStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return article;
    }
}
