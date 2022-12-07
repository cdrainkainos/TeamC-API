package com.kainos.ea.controller;

import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.dao.UserDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.RoleNotExistException;
import com.kainos.ea.exception.UserAlreadyExistsException;
import com.kainos.ea.model.User;
import com.kainos.ea.service.RolesService;
import com.kainos.ea.service.UserService;
import io.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team C Sprint")
@Path("/api")

public class WebService {

    private static RolesService rolesService;
    private static UserService userService;

    public WebService(){
        RolesDao rolesDao = new RolesDao();
        UserDao userDao = new UserDao();
        DatabaseConnection databaseConnector = new DatabaseConnection();
        rolesService = new RolesService(rolesDao, databaseConnector);
        userService = new UserService(userDao, databaseConnector);

    }

    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles()
    {
        try {
            return Response.status(HttpStatus.OK_200).entity(rolesService.getAllRoles()).build();
        } catch (Exception | DatabaseConnectionException e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        }

    }

    @GET
    @Path("/job-specification/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecification(@PathParam("id") int role_id)
    {
        try {
            return Response.status(HttpStatus.OK_200).entity(rolesService.getAllSpecifications(role_id)).build();
        } catch (RoleNotExistException e){
            return Response.status(HttpStatus.NOT_FOUND_404, e.getMessage()).build();
        } catch (DatabaseConnectionException | Exception e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) throws DatabaseConnectionException, UserAlreadyExistsException {
        try {
            User user1 = userService.registerUser(user);
            return Response.status(HttpStatus.CREATED_201).entity(user1).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
}
