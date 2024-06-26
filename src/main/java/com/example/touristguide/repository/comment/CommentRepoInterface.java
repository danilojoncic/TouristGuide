package com.example.touristguide.repository.comment;

import com.example.touristguide.domain.Comment;
import com.example.touristguide.dto.CreateCommentDto;

import java.util.List;

public interface CommentRepoInterface {
    List<Comment> getAllCommentsForAPost(int article_id,int page, int pageSize);

    void deleteComment(int comment_id);

    void addComment(int post_id, CreateCommentDto createCommentDto);
}
