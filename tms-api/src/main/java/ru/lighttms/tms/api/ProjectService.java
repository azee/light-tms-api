package ru.lighttms.tms.api;

import org.springframework.beans.factory.annotation.Autowired;
import ru.lighttms.tms.api.repositories.ProjectRepository;
import ru.lighttms.tms.beans.Project;
import ru.lighttms.tms.helpers.MongoHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */

@Path("/project")
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    /**
     * Return all projects
     * @return object List<Project>
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/all")
    public List<Project> getAllProject() throws Exception {
        return (List)projectRepository.findAll();

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
        return projectRepository.findOne(id);
    }

    /**
     * Create a Project
     * @param - Project
     */
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public Response createProject(Project project) throws Exception {
        projectRepository.save(project);
        return Response.ok().build();
    }

    /**
     * Update a Project
     * @param - Project
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public Response updateProject(Project project) throws Exception {
        projectRepository.save(project);
        return Response.ok().build();
    }

    /**
     * Remove a Project
     * @param - String id
     */
    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Response removeProject(@PathParam("id") final String id) throws Exception {
        projectRepository.delete(id);
        return Response.ok().build();
    }

}
