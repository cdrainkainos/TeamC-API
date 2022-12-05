package com.kainos.ea.controller;

import com.kainos.ea.dao.BandsDao;
import com.kainos.ea.dao.FamilyDao;
import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.*;
import com.kainos.ea.exception.validation.UrlNotValidException;
import com.kainos.ea.model.JobRoleXL;
import com.kainos.ea.service.BandsService;
import com.kainos.ea.service.FamiliesService;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.RoleNotExistException;
import com.kainos.ea.service.RolesService;
import com.kainos.ea.validator.JobRoleValidator;
import io.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team C Sprint")
@Path("/api")

public class WebService {

    private static RolesService rolesService;
    private static BandsService bandsService;
    private static FamiliesService familiesService;

    private static JobRoleValidator jobRoleValidator;

    public WebService(){
        RolesDao rolesDao = new RolesDao();
        BandsDao bandsDao = new BandsDao();
        FamilyDao familyDao = new FamilyDao();
        DatabaseConnection databaseConnector = new DatabaseConnection();
        rolesService = new RolesService(rolesDao, databaseConnector);
        bandsService = new BandsService(bandsDao, databaseConnector);
        familiesService = new FamiliesService(familyDao, databaseConnector);
        jobRoleValidator = new JobRoleValidator();
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
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoleById(@PathParam("id") int id){
        try {
            return Response.status(HttpStatus.OK_200).entity(rolesService.getRoleById(id)).build();
        } catch (JobRoleDoesNotExistException e){
            return Response.status(HttpStatus.NOT_FOUND_404, e.getMessage()).build();
        } catch (Exception | DatabaseConnectionException  e){
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        }
    }

    @PUT
    @Path("/job-roles/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJobRole(@PathParam("id") int roleID,  JobRoleXL jobRoleXL){
        try{
            if (jobRoleValidator.isValidJobRole(jobRoleXL)){
                try {
                    int returnedId = rolesService.updateJobRole(roleID, jobRoleXL);
                    return Response.status(HttpStatus.OK_200).entity(returnedId).build();
                } catch (Exception | DatabaseConnectionException e) {
                    return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
                }
            } else {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
        } catch (UrlNotValidException e) {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }

    @GET
    @Path("/bands")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBands(){
        try{
            return Response.status(HttpStatus.OK_200).entity(bandsService.getAllBands()).build();
        } catch (Exception | DatabaseConnectionException e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        }
    }

    @GET
    @Path("/job-families")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamilies(){
        try{
            return Response.status(HttpStatus.OK_200).entity(familiesService.getAllFamilies()).build();
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
}
