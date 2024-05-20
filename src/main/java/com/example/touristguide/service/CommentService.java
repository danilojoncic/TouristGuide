package com.example.touristguide.service;

import com.example.touristguide.domain.Comment;
import com.example.touristguide.dto.CreateArticleDto;
import com.example.touristguide.dto.CreateCommentDto;
import com.example.touristguide.repository.comment.CommentRepoInterface;

import javax.inject.Inject;
import java.util.List;

public class CommentService {
    @Inject private CommentRepoInterface commentRepoInterface;

    public List<Comment> getAllCommentsForArticle(int article_id){
        return commentRepoInterface.getAllCommentsForAPost(article_id);
    }

    public void addCommentForArticle(int article_id, CreateCommentDto createCommentDto){
        //kasnije dodati boolean da li je okej radi provjere
        commentRepoInterface.addComment(article_id,createCommentDto);
    }

    public void deleteComment(int comment_id){
        //isto dodati neki boolean radi provjere
        commentRepoInterface.deleteComment(comment_id);
    }

}
