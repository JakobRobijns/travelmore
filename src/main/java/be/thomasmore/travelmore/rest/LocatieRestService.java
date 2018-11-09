package be.thomasmore.travelmore.rest;

import be.thomasmore.travelmore.domain.Locatie;
import be.thomasmore.travelmore.service.LocatieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by freman on 18/10/2018.
 */
@Path("/locations")
public class LocatieRestService {

    @Inject
    private LocatieService locationService;

    @GET
    @Path("/getlocation")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Locatie getLocationById(@QueryParam("id") int id) {
        return locationService.findLocationById(id);
    }

    @POST
    @Path("/addlocation")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addLocation(Locatie locatie) {
        if (null != locationService.findLocationById(locatie.getId())) {
            return Response
                    .status(Response.Status.NOT_MODIFIED)
                    .entity("location id should not be set.").build();
        }

        locationService.insert(locatie);
        return Response.status(Response.Status.CREATED).entity(locatie).build();
    }
}
