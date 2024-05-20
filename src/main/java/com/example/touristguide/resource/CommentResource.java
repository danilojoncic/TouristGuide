package com.example.touristguide.resource;

import com.example.touristguide.domain.Comment;
import com.example.touristguide.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


public class CommentResource {


    @Inject private CommentService commentService;

    @GET
    @Path("/{article_id}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getAllCommentsForArticle(){
        return null;
    }

}
