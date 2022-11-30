package com.kainos.ea.controller;

import com.kainos.ea.dao.BandsDao;
import com.kainos.ea.dao.CapabilityDao;
import com.kainos.ea.dao.FamilyDao;
import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.*;
import com.kainos.ea.service.BandsService;
import com.kainos.ea.service.CapabilitiesService;
import com.kainos.ea.service.FamiliesService;
import com.kainos.ea.service.RolesService;
import io.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Engineering Academy Dropwizard API")
@Path("/api")

public class WebService {

    private static RolesService rolesService;
    private static BandsService bandsService;
    private static FamiliesService familiesService;
    private static CapabilitiesService capabilitiesService;

    public WebService(){
        RolesDao rolesDao = new RolesDao();
        BandsDao bandsDao = new BandsDao();
        FamilyDao familyDao = new FamilyDao();
        CapabilityDao capabilityDao = new CapabilityDao();
        DatabaseConnection databaseConnector = new DatabaseConnection();
        rolesService = new RolesService(rolesDao, databaseConnector);
        bandsService = new BandsService(bandsDao, databaseConnector);
        familiesService = new FamiliesService(familyDao, databaseConnector);
        capabilitiesService = new CapabilitiesService(capabilityDao, databaseConnector);
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
        } catch (Exception | DatabaseConnectionException  e){
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        } catch (JobRoleDoesNotExistException e){
            return Response.status(HttpStatus.NOT_FOUND_404, e.getMessage()).build();
        }
    }

    @GET
    @Path("/bands/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandById(@PathParam("id") int id){
        try {
            return Response.status(HttpStatus.OK_200).entity(bandsService.getBandById(id)).build();
        } catch (Exception | DatabaseConnectionException  e){
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        } catch (BandDoesNotExistException e){
            return Response.status(HttpStatus.NOT_FOUND_404, e.getMessage()).build();
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
    @Path("/job-families/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamilyById(@PathParam("id") int id){
        try {
            return Response.status(HttpStatus.OK_200).entity(familiesService.getFamilyById(id)).build();
        } catch (Exception | DatabaseConnectionException  e){
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        } catch (FamilyDoesNotExistException e){
            return Response.status(HttpStatus.NOT_FOUND_404, e.getMessage()).build();
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
    @Path("/capabilities/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilityById(@PathParam("id") int id){
        try {
            return Response.status(HttpStatus.OK_200).entity(capabilitiesService.getCapabilityById(id)).build();
        } catch (Exception | DatabaseConnectionException  e){
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        } catch (CapabilityDoesNotExistException e){
            return Response.status(HttpStatus.NOT_FOUND_404, e.getMessage()).build();
        }
    }

    @GET
    @Path("/capabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilities(){
        try{
            return Response.status(HttpStatus.OK_200).entity(capabilitiesService.getAllCapabilities()).build();
        } catch (Exception | DatabaseConnectionException e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500, e.getMessage()).build();
        }
    }

}
