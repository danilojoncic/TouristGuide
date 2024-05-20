package com.example.touristguide.repository.comment;

import com.example.touristguide.domain.Comment;
import com.example.touristguide.dto.CreateCommentDto;
import com.example.touristguide.repository.MDBRepository;

import java.util.List;

public class CommentRepo extends MDBRepository implements CommentRepoInterface {
    @Override
    public List<Comment> getAllCommentsForAPost(int post_id) {
        return null;
    }

    @Override
    public void deleteComment(int comment_id) {

    }

    @Override
    public void addComment(int post_id, CreateCommentDto createCommentDto) {

    }
}
