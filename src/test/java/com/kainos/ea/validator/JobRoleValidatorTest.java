package com.kainos.ea.validator;
import com.kainos.ea.exception.validation.UrlNotValidException;
import com.kainos.ea.model.JobRoleRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JobRoleValidatorTest {

    JobRoleValidator jobRoleValidator = new JobRoleValidator();

    @Test
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole() throws UrlNotValidException {

        JobRoleRequest testJobRoleRequest = new JobRoleRequest(
                1,
                1,
                1,
                "test role title",
                "test job spec",
                "https://kainossoftwareltd.sharepoint.com/:w:/r/engineering/_layouts/15/Doc.aspx?sourcedoc=%7B17482B35-3A5B-41A7-A55A-70F5B45E0549%7D&file=Test%20Engineer%20(A).docx&action=default&mobileredirect=true"
        );
        assertTrue(jobRoleValidator.isValidJobRole(testJobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldThrowUrlNotValidException_whenLinkDontMatchRegex(){
        JobRoleRequest testJobRoleRequest = new JobRoleRequest(
                1,
                1,
                1,
                "test role title",
                "test job spec",
                "kainossoftwareltd.sharepoint.com/:w:/r/engineering/_layouts/15/Doc.aspx?sourcedoc=%7B17482B35-3A5B-41A7-A55A-70F5B45E0549%7D&file=Test%20Engineer%20(A).docx&action=default&mobileredirect=true"
        );
        assertThrows(UrlNotValidException.class, ()-> jobRoleValidator.isValidJobRole(testJobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobSpecificationIsMoreThan255Chars() throws UrlNotValidException {
        JobRoleRequest testJobRoleRequest = new JobRoleRequest(
                1,
                1,
                1,
                "test role title",
                "test job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spectest job spec",
                "https://kainossoftwareltd.sharepoint.com/:w:/r/engineering/_layouts/15/Doc.aspx?sourcedoc=%7B17482B35-3A5B-41A7-A55A-70F5B45E0549%7D&file=Test%20Engineer%20(A).docx&action=default&mobileredirect=true"
        );
        assertFalse(jobRoleValidator.isValidJobRole(testJobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobRoleTitleIsMoreThan35Chars() throws UrlNotValidException {
        JobRoleRequest testJobRoleRequest = new JobRoleRequest(
                1,
                1,
                1,
                "test role title test role titletest role titletest role titletest role titletest role titletest role titletest role titletest role titletest role title",
                "test job spec",
                "https://kainossoftwareltd.sharepoint.com/:w:/r/engineering/_layouts/15/Doc.aspx?sourcedoc=%7B17482B35-3A5B-41A7-A55A-70F5B45E0549%7D&file=Test%20Engineer%20(A).docx&action=default&mobileredirect=true"
        );
        assertFalse(jobRoleValidator.isValidJobRole(testJobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenBandIdIsMoreThan255() throws UrlNotValidException {
        JobRoleRequest testJobRoleRequest = new JobRoleRequest(
                1,
                300,
                1,
                "test role title",
                "test job spec",
                "https://kainossoftwareltd.sharepoint.com/:w:/r/engineering/_layouts/15/Doc.aspx?sourcedoc=%7B17482B35-3A5B-41A7-A55A-70F5B45E0549%7D&file=Test%20Engineer%20(A).docx&action=default&mobileredirect=true"
        );
        assertFalse(jobRoleValidator.isValidJobRole(testJobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenBandIdIsLessThan0() throws UrlNotValidException {
        JobRoleRequest testJobRoleRequest = new JobRoleRequest(
                1,
                -100,
                1,
                "test role title",
                "test job spec",
                "https://kainossoftwareltd.sharepoint.com/:w:/r/engineering/_layouts/15/Doc.aspx?sourcedoc=%7B17482B35-3A5B-41A7-A55A-70F5B45E0549%7D&file=Test%20Engineer%20(A).docx&action=default&mobileredirect=true"
        );
        assertFalse(jobRoleValidator.isValidJobRole(testJobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenFamilyIdIsLessThan0() throws UrlNotValidException {
        JobRoleRequest testJobRoleRequest = new JobRoleRequest(
                1,
                1,
                -100,
                "test role title",
                "test job spec",
                "https://kainossoftwareltd.sharepoint.com/:w:/r/engineering/_layouts/15/Doc.aspx?sourcedoc=%7B17482B35-3A5B-41A7-A55A-70F5B45E0549%7D&file=Test%20Engineer%20(A).docx&action=default&mobileredirect=true"
        );
        assertFalse(jobRoleValidator.isValidJobRole(testJobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenFamilyIdIsMoreThan255() throws UrlNotValidException {
        JobRoleRequest testJobRoleRequest = new JobRoleRequest(
                1,
                1,
                300,
                "test role title",
                "test job spec",
                "https://kainossoftwareltd.sharepoint.com/:w:/r/engineering/_layouts/15/Doc.aspx?sourcedoc=%7B17482B35-3A5B-41A7-A55A-70F5B45E0549%7D&file=Test%20Engineer%20(A).docx&action=default&mobileredirect=true"
        );
        assertFalse(jobRoleValidator.isValidJobRole(testJobRoleRequest));
    }
}
