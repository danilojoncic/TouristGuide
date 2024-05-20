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


    @GET
    @Path("/latest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatestArticles(){
        return Response.ok(articleService.getLatestArticles()).build();
    }

    @GET
    @Path("/popular")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPopularArticles(){
        return Response.ok(articleService.getPopularArticles()).build();    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArticles(){
        return Response.ok(articleService.getAllArticles()).build();
    }


    @GET
    @Path("/parameter/{string}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticlesByTagOrDestination(@PathParam("string")String parameter){
        return Response.ok(articleService.getArticlesBasedOnCriterium(parameter)).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createArticle(@Valid CreateArticleDto createArticleDto){
        System.out.println("POST CALLED");
        articleService.addArticle(createArticleDto);
        return Response.ok("New article has been added!").build();
    }



    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editArticle(@PathParam("id")Integer id,@Valid CreateArticleDto createArticleDto){
        articleService.editArticle(id,createArticleDto);
        return Response.ok("Article with id: " + id + " has been edited!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArticle(@PathParam("id")Integer id){
        articleService.deleteArticle(id);
        return Response.ok("Article with id: " + id + " has been deleted!").build();
    }
}
