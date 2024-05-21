package com.example.touristguide.resource;

import com.example.touristguide.dto.CreateActivityDTO;
import com.example.touristguide.dto.CreateDestinationDTO;
import com.example.touristguide.service.ActivityService;
import com.example.touristguide.service.DestinationService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/activity")
public class ActivityResource {
    @Inject
    private ActivityService activityService;


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        return Response.ok(this.activityService.getOne(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDestinations(@QueryParam("page")@DefaultValue("1")int page,
                                       @QueryParam("pageSize")@DefaultValue("10")int pageSize){
        return Response.ok(this.activityService.getAll(page,pageSize)).build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDestination(@PathParam("id")Integer id, @Valid CreateActivityDTO createActivityDTO){
        activityService.update(createActivityDTO,id);
        return Response.ok("This destination has been updated!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDestination(@PathParam("id")Integer id){
        activityService.delete(id);
        return Response.ok("This destinations has been deleted!").build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDestination(@Valid CreateActivityDTO createActivityDTO){
        activityService.add(createActivityDTO);
        return Response.ok("New destination has been added").build();
    }
}
