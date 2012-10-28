package ru.lighttms.tms.api;

import ru.lighttms.tms.beans.Project;
import ru.lighttms.tms.helpers.MongoHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */

@Path("/project")
public class ProjectService {

    /**
     * Return all projects
     * @return object List<Project>
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/all")
    public List<Project> getAllProject() throws Exception {
        return MongoHelper.getAllProjects();
    }

    /**
     * Return project by id
     * @return object Project
     * @param - String id
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Project getProject(@PathParam("id") final String id) throws Exception {
        return MongoHelper.getProject(id);
    }

    /**
     * Create a Project
     * @param - Project
     */
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public ResponseBuilder createProject(Project project) throws Exception {
        MongoHelper.createProject(project);
        return Response.ok();
    }

    /**
     * Update a Project
     * @param - Project
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public ResponseBuilder updateProject(Project project) throws Exception {
        MongoHelper.updateProject(project);
        return Response.ok();
    }

    /**
     * Remove a Project
     * @param - String id
     */
    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public ResponseBuilder removeProject(@PathParam("id") final String id) throws Exception {
        MongoHelper.removeProject(id);
        return Response.ok();
    }

}
