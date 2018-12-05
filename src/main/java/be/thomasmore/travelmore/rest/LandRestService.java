package be.thomasmore.travelmore.rest;

import be.thomasmore.travelmore.domain.Land;
import be.thomasmore.travelmore.service.LandService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import  org.jboss.resteasy.core.messagebody.WriterUtility;

/**
 * Created by Bram on 5/12/2018.
 */
@Path("/landen")
public class LandRestService{

    @Inject
    private LandService landService;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getLanden() {

        return Response
                .status(Response.Status.OK)
                .entity((List<Land>)landService.findAllLanden()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getLandById(@PathParam("id") int id) {
        return Response
                .status(Response.Status.OK)
                .entity(landService.findLandById(id)).build();
    }

    @POST
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addLocation(Land land) {
        if (null != landService.findLandById(land.getId())) {
            return Response
                    .status(Response.Status.NOT_MODIFIED)
                    .entity("location id should not be set.").build();
        }

        landService.insert(land);
        return Response.status(Response.Status.CREATED).entity(land).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public  Response deleteLocation(@PathParam("id") int id, @HeaderParam("Authorization") String authHeader) {
        if(null ==  landService.findLandById(id)){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Location you provided is not found on our system").build();
        }
        try {

            if(!authHeader.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")){
                return  Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("Your are not permitted deleting this resource").build();
            }
        }catch (Exception err){
            return  Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Your are not permitted deleting this resource").build();
        }
        landService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).entity("Location deleted").build();
    }

}
