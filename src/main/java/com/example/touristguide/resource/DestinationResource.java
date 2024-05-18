package com.example.touristguide.resource;


import com.example.touristguide.dto.CreateDestinationDTO;
import com.example.touristguide.service.DestinationService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/destination")
public class DestinationResource {


    @Inject
    private DestinationService destinationService;


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        return Response.ok(this.destinationService.getOne(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDestinations(){
        return Response.ok(this.destinationService.getAll()).build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDestination(@PathParam("id")Integer id, @Valid CreateDestinationDTO createDestinationDTO){
        destinationService.update(createDestinationDTO,id);
        return Response.ok("This destination has been updated!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDestination(@PathParam("id")Integer id){
        destinationService.delete(id);
        return Response.ok("This destinations has been deleted!").build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDestination(@Valid CreateDestinationDTO createDestinationDTO){
        destinationService.add(createDestinationDTO);
        return Response.ok("New destination has been added").build();
    }
}
