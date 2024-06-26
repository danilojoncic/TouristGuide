package com.example.touristguide.resource;

import com.example.touristguide.domain.user.Status;
import com.example.touristguide.domain.user.User;
import com.example.touristguide.dto.CreateUserDto;
import com.example.touristguide.dto.UserJWTResponse;
import com.example.touristguide.dto.UserLoginDto;
import com.example.touristguide.dto.UserUpdateDto;
import com.example.touristguide.requestFilters.JWTCoder;
import com.example.touristguide.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    @Inject private UserService userService;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@Valid UserLoginDto userLoginDto){
        User user = userService.login(userLoginDto);
        if(user == null){
            System.out.println("User is null");
            return Response.status(404).build();
        }
        if(user.getStatus().equals(Status.blocked)) return Response.status(401).build();
        UserJWTResponse userJWTResponse = JWTCoder.encode(user.getUser_id(),user.getFirstname(),user.getLastname(),String.valueOf(user.getTip()));
        return Response.ok(userJWTResponse).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response tableOfUsers(
            @QueryParam("page")@DefaultValue("1")int page,
            @QueryParam("pageSize")@DefaultValue("5")int pageSize){

        return Response.ok(userService.getAllUsersInTable(page,pageSize)).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid CreateUserDto createUserDto){
        if(userService.addUser(createUserDto)){
            return Response.ok("User with credentials: " + createUserDto.getFirstname() + " " + createUserDto.getLastname() + " has been added").build();
        }else{
            return Response.status(409,"user with same credentials already exists!").build();

        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id){
        userService.delete(id);
        return Response.ok("User with id " + id + " has been deleted!").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id")Integer id){
        return Response.ok(userService.findById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editOne(@PathParam("id")Integer id, @Valid UserUpdateDto userUpdateDto){
        userService.edit(id,userUpdateDto);
        return Response.ok("User with id " + id + " has been updated!").build();
    }

    @PUT
    @Path("/{id}/{status}")
    public Response active_blocked(@PathParam("id")Integer id,@PathParam("status")String status){
        userService.changeStatus(id,status);
        return Response.ok("User with id " + id + " has his status set to " + status).build();
    }
}
