package com.kainos.ea.integration;

import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobRoleXL;
import com.kainos.ea.trueApplication;
import com.kainos.ea.trueConfiguration;
import com.kainos.ea.model.JobRole;
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
    void getRBandById_shouldReturnBand() {
        int testID = 1;
        JobRoleXL response = APP.client().target("http://localhost:8080/api/bands/1")
                .request()
                .get(JobRoleXL.class);
        assertNotNull(response);
    }



}
