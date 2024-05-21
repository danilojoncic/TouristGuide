package com.example.touristguide.resource;

import com.example.touristguide.domain.Comment;
import com.example.touristguide.dto.CreateCommentDto;
import com.example.touristguide.service.CommentService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/comment")
public class CommentResource {
    @Inject private CommentService commentService;

    @GET
    @Path("/{article_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCommentsForArticle(@PathParam("article_id")int article_id,
                                             @QueryParam("page")@DefaultValue("1")int page,
                                             @QueryParam("pageSize")@DefaultValue("10")int pageSize) {
        return Response.ok(commentService.getAllCommentsForArticle(article_id,page,pageSize)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteComment(@PathParam("id")Integer id){
        commentService.deleteComment(id);
        return Response.ok("Comment with id " + id + " has been deleted!").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{article_id}")
    public Response addComment(@PathParam("article_id")Integer article_id, @Valid CreateCommentDto createCommentDto){
        commentService.addCommentForArticle(article_id,createCommentDto);
        return Response.ok("Comment " + createCommentDto.getText() +" by :" + createCommentDto.getAuthor_name() + " has been created!").build();
    }
}
