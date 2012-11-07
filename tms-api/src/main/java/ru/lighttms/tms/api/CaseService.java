package ru.lighttms.tms.api;

import ru.lighttms.tms.beans.TestCase;
import ru.lighttms.tms.helpers.MongoHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by IntelliJ IDEA.
 * User: azee
 */


@Path("/testcase")
public class CaseService {

     /**
     * Return testcase by id
     * @return object TestCase
     * @param - String id
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public TestCase getTestCase(@PathParam("id") final String id) throws Exception {
        return MongoHelper.getTestCase(id);
    }

    /**
     * Create a TestCase
     * @param - TestCase
     */
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public Response createTestCase(TestCase testCase) throws Exception {
        MongoHelper.createTestCase(testCase);
        return Response.ok().build();
    }

    /**
     * Update a TestCase
     * @param - TestCase
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public Response updateTestCase(TestCase testCase) throws Exception {
        MongoHelper.updateTestCase(testCase);
        return Response.ok().build();
    }

    /**
     * Remove a TestCase
     * @param - String id
     */
    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Response removeTestCase(@PathParam("id") final String id) throws Exception {
        MongoHelper.removeTestCase(id);
        return Response.ok().build();
    }




}
