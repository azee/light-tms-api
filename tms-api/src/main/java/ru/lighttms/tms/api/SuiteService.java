package ru.lighttms.tms.api;

import ru.lighttms.tms.beans.Suite;
import ru.lighttms.tms.helpers.MongoHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



/**
 * Created by IntelliJ IDEA.
 * User: azee
 */


@Path("/suite")
public class SuiteService {

     /**
     * Return suite by id
     * @return object Suite
     * @param - String id
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Suite getSuite(@PathParam("id") final String id) throws Exception {
        return MongoHelper.getSuite(id);
    }

    /**
     * Create a Suite
     * @param - Suite
     */
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public Response createSuite(Suite suite) throws Exception {
        MongoHelper.createSuite(suite);
        return Response.ok().build();
    }

    /**
     * Update a Suite
     * @param - Suite
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public Response updateSuite(Suite suite) throws Exception {
        MongoHelper.updateSuite(suite);
        return Response.ok().build();
    }

    /**
     * Remove a Suite
     * @param - String id
     */
    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Response removeSuite(@PathParam("id") final String id) throws Exception {
        MongoHelper.removeSuite(id);
        return Response.ok().build();
    }




}
