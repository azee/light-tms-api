package ru.lighttms.tms.api;

import ru.lighttms.tms.beans.Suite;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



/**
 * Created by IntelliJ IDEA.
 * User: azee
 */


@Path("/suite")
public class SuiteService {

    /**
     * Return top trigger by it's file name
     * @return object Trigger
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Suite getSuite(@PathParam("id") final String id) {
        return new Suite();
    }




}
