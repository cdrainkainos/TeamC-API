package com.kainos.ea.controller;

import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.dao.SpecificationsDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.service.RolesService;
import com.kainos.ea.service.SpecificationsService;
import io.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Engineering Academy Dropwizard API")
@Path("/api")

public class WebService {

    private static RolesService rolesService;
    private static SpecificationsService specificationsService;

    public WebService(){
        RolesDao rolesDao = new RolesDao();
        SpecificationsDao specificationsDao = new SpecificationsDao();
        DatabaseConnection databaseConnector = new DatabaseConnection();
        rolesService = new RolesService(rolesDao, databaseConnector);
        specificationsService = new SpecificationsService(specificationsDao, databaseConnector);
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
    @Path("/job-specification")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecification()
    {
        try {
            return Response.status(HttpStatus.OK_200).entity(specificationsService.getAllSpecifications()).build();
        } catch (Exception | DatabaseConnectionException e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        }

    }
}
