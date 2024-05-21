package com.example.touristguide.repository.comment;

import com.example.touristguide.domain.Comment;
import com.example.touristguide.dto.ArticlePresentationDto;
import com.example.touristguide.dto.CreateCommentDto;
import com.example.touristguide.repository.MDBRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentRepo extends MDBRepository implements CommentRepoInterface {
    @Override
    public List<Comment> getAllCommentsForAPost(int post_id,int page, int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Comment> comments = new ArrayList<>();
        int offset = (page - 1)* pageSize;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM comment WHERE article_id = ? LIMIT ? OFFSET ?"
            );
            preparedStatement.setInt(1,post_id);
            preparedStatement.setInt(2,pageSize);
            preparedStatement.setInt(3,offset);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setComment_id(resultSet.getInt("comment_id"));
                comment.setAuthor_name(resultSet.getString("author_name"));
                comment.setText(resultSet.getString("text"));
                comment.setCreated_at(resultSet.getDate("created_at"));
                //post_id isto kao i article_id ali kad vec uzimamo sve iz baze sto da ne
                comment.setArticle_id(resultSet.getInt("article_id"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return comments;
    }

    @Override
    public void deleteComment(int comment_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM comment WHERE comment_id = ?");
            preparedStatement.setInt(1, comment_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public void addComment(int post_id, CreateCommentDto createCommentDto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO comment" +
                            " ( comment_id, author_name, text, article_id)"
                            + "VALUES(NULL,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,createCommentDto.getAuthor_name());
            preparedStatement.setString(2,createCommentDto.getText());
            preparedStatement.setInt(3,post_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
