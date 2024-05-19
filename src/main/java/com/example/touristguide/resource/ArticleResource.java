package com.example.touristguide.resource;

import com.example.touristguide.dto.CreateArticleDto;
import com.example.touristguide.service.ArticleService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Path("/article")
public class ArticleResource {

    @Inject
    private ArticleService articleService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneArticle(@PathParam("id")Integer id){
        return Response.ok(articleService.getOneArticle(id)).build();
    }


//    @GET
//    @Path("/latest")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getLatestArticles(){
//        return null;
//    }
//
//    @GET
//    @Path("/popular")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getPopularArticles(){
//        return null;
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArticles(){
        return Response.ok(articleService.getAllArticles()).build();
    }


//    @GET
//    @Path("/{string}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getArticlesByTagOrDestination(@PathParam("string")String parameter){
//        return null;
//    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createArticle(@Valid CreateArticleDto createArticleDto){
        System.out.println("POST CALLED");
        articleService.addArticle(createArticleDto);
        return Response.ok("New article has been added!").build();
    }



//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response editArticle(@Valid CreateArticleDto createArticleDto){
//        return null;
//    }

    @DELETE
    @Path("/{id}")
    public Response deleteArticle(@PathParam("id")Integer id){
        articleService.deleteArticle(id);
        return Response.ok("Article with id: " + id + " has been deleted!").build();
    }
}
