package com.kainos.ea.controller;

import com.kainos.ea.dao.CompetencyDao;
import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.CompetencyPerBandLvlNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.RoleNotExistException;
import com.kainos.ea.service.CompetencyService;
import com.kainos.ea.service.RolesService;
import io.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team C Sprint")
@Path("/api")

public class WebService {

    private static RolesService rolesService;
    private static CompetencyService competencyService;
    public WebService(){
        RolesDao rolesDao = new RolesDao();
        CompetencyDao competencyDao = new CompetencyDao();
        DatabaseConnection databaseConnector = new DatabaseConnection();
        rolesService = new RolesService(rolesDao, databaseConnector);
        competencyService = new CompetencyService(competencyDao, databaseConnector);
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
    @GET
    @Path("/competencies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandCompetency(@PathParam("id") int band_id)
    {
        try {
            return Response.status(HttpStatus.OK_200).entity(competencyService.getAllCompetencyPerBandLvl(band_id)).build();
        } catch (CompetencyPerBandLvlNotExistException e){
            return Response.status(HttpStatus.NOT_FOUND_404, e.getMessage()).build();
        } catch (DatabaseConnectionException | Exception e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        }
    }
}
