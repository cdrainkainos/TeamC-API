package com.kainos.ea.integration;

import com.kainos.ea.model.*;
import com.kainos.ea.trueApplication;
import com.kainos.ea.trueConfiguration;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(DropwizardExtensionsSupport.class)
public class WebServiceIntegrationTest {

    static final DropwizardAppExtension<trueConfiguration> APP = new DropwizardAppExtension<>(
            trueApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );


    @Test
    void getEmployees_shouldReturnListOfEmployees() {
        List<JobRole> response = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);
        assertTrue(response.size() > 0);
    }

    @Test
    void getRoleById_shouldReturnJobRole() {
        int testID = 1;
        JobRoleXL response = APP.client().target("http://localhost:8080/api/job-roles/1")
                .request()
                .get(JobRoleXL.class);
        assertNotNull(response);
    }

    @Test
    void getBands_shouldReturnListOfBands() {
        List<Band> response = APP.client().target("http://localhost:8080/api/bands")
                .request()
                .get(List.class);
        assertTrue(response.size() > 0);
    }

    @Test
    void getBandById_shouldReturnBand() {
        int testID = 1;
        Band response = APP.client().target("http://localhost:8080/api/bands/1")
                .request()
                .get(Band.class);
        assertNotNull(response);
    }

    @Test
    void getCapabilities_shouldReturnListOfCapabilities() {
        List<Capability> response = APP.client().target("http://localhost:8080/api/capabilities")
                .request()
                .get(List.class);
        assertTrue(response.size() > 0);
    }

    @Test
    void getCapabilityById_shouldReturnCapability() {
        int testID = 1;
        Capability response = APP.client().target("http://localhost:8080/api/capabilities/1")
                .request()
                .get(Capability.class);
        assertNotNull(response);
    }

    @Test
    void getFamilies_shouldReturnListOfCapabilities() {
        List<JobFamily> response = APP.client().target("http://localhost:8080/api/job-families")
                .request()
                .get(List.class);
        assertTrue(response.size() > 0);
    }

    @Test
    void getFamilyById_shouldReturnFamily() {
        int testID = 1;
        JobFamily response = APP.client().target("http://localhost:8080/api/job-families/1")
                .request()
                .get(JobFamily.class);
        assertNotNull(response);
    }
}
