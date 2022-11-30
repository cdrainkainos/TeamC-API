package com.kainos.ea.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.model.JobSpecification;
import com.kainos.ea.trueApplication;
import com.kainos.ea.trueConfiguration;
import com.kainos.ea.model.JobRole;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

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
    void getSpecification_shouldReturnSpecification() {
        JobSpecification response = APP.client().target("http://localhost:8080/api/job-specification/1")
                .request()
                .get(JobSpecification.class);
        Assertions.assertNotNull(response);
    }

    @Test
    void getSpecificationById_shouldReturnResponseEqualToTestSpecification() {
        JobSpecification jTest = new JobSpecification(
                "Principle Data Architect",
                "As a Principal Data Architect in Kainos,  youll be accountable for successful delivery of data solutions across multiple customers",
                "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?csf=1&web=1&e=iExkns&siteid=%7B7D710350%2D0135%2D457B%2DB601%2D6CED1D6C94AF%7D&webid=%7BAB4B0C68%2D5BFC%2D4CCD%2D94E2%2D294A4EDE9CEF%7D&uniqueid=%7BEF88E872%2D0790%2D4482%2DB9EE%2DD65121B7ED38%7D&FolderCTID=0x012000BBCDBD83EDBB3F4180056411A9CB934B&id=%2Fpeople%2FJob%20Specifications%2FData%2FJob%20profile%20%2D%20Principal%20Data%20Architect%20%28P%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FData");
        ObjectMapper mapper = new ObjectMapper();
        JobSpecification response = APP.client().target("http://localhost:8080/api/job-specification/1")
                .request()
                .get(JobSpecification.class);
        Assertions.assertEquals(response.getKainosJobTitle(), jTest.getKainosJobTitle());
        Assertions.assertEquals(response.getJobSpecification(), jTest.getJobSpecification());
        Assertions.assertEquals(response.getJobSpecificationLink(), jTest.getJobSpecificationLink());
    }
}
