package com.kainos.ea.controller;

import com.kainos.ea.dao.BandsDao;
import com.kainos.ea.dao.FamilyDao;
import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.dao.UserDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.*;
import com.kainos.ea.exception.validation.UrlNotValidException;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.service.BandsService;
import com.kainos.ea.service.FamiliesService;
import com.kainos.ea.dao.CompetencyDao;
import com.kainos.ea.model.User;
import com.kainos.ea.service.RolesService;
import com.kainos.ea.service.UserService;
import com.kainos.ea.service.CompetencyService;
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
    private static CompetencyService competencyService;
    private static JobRoleValidator jobRoleValidator;
    private static UserService userService;

    public WebService(){
        RolesDao rolesDao = new RolesDao();
        BandsDao bandsDao = new BandsDao();
        FamilyDao familyDao = new FamilyDao();
        CompetencyDao competencyDao = new CompetencyDao();
        UserDao userDao = new UserDao();
        DatabaseConnection databaseConnector = new DatabaseConnection();
        rolesService = new RolesService(rolesDao, databaseConnector);
        bandsService = new BandsService(bandsDao, databaseConnector);
        familiesService = new FamiliesService(familyDao, databaseConnector);
        competencyService = new CompetencyService(competencyDao, databaseConnector);
        jobRoleValidator = new JobRoleValidator();
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
    public Response updateJobRole(@PathParam("id") int roleID,  JobRoleRequest jobRoleRequest){
        try{
            if (jobRoleValidator.isValidJobRole(jobRoleRequest)){
                try {
                    boolean responseStatus = rolesService.updateJobRole(roleID, jobRoleRequest);
                    return Response.status(HttpStatus.OK_200).entity(responseStatus).build();
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

    @GET
    @Path("/competencies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandCompetency(@PathParam("id") int band_id)
    {
        try {
            return Response.status(HttpStatus.OK_200).entity(competencyService.getAllCompetencyPerBandLvl(band_id)).build();
        } catch (BandNotExistException e){
            return Response.status(HttpStatus.NOT_FOUND_404, e.getMessage()).build();
        } catch (DatabaseConnectionException | Exception e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        }
    }

    @POST
    @Path("/create-job-role")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobRole(JobRoleRequest jobRoleReq){
        try{
            if (jobRoleValidator.isValidJobRole(jobRoleReq)){
                try {
                    int id = rolesService.createJobRole(jobRoleReq);
                    return Response.status(HttpStatus.CREATED_201).entity(id).build();
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
