package com.kainos.ea.validator;

import com.kainos.ea.exception.validation.UrlNotValidException;
import com.kainos.ea.model.JobRoleRequest;
import java.util.regex.Pattern;

public class JobRoleValidator {

    public boolean isValidJobRole(JobRoleRequest jobRoleRequest) throws UrlNotValidException {

        if (jobRoleRequest.getBandId() > 255 || jobRoleRequest.getBandId()<=0){
            return false;
        }

        if (jobRoleRequest.getJobFamilyId() > 255 || jobRoleRequest.getJobFamilyId() <=0){
            return false;
        }

        if (jobRoleRequest.getRole_title().length() > 35){
            return false;
        }

        if (jobRoleRequest.getJobSpecification().length() > 255){
            return false;
        }

        String linkRegex = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";
        Pattern pattern = Pattern.compile(linkRegex);
        if (!pattern.matcher(jobRoleRequest.getJobSpecLink()).matches()){
            System.out.println(jobRoleRequest.getJobSpecLink());
            throw new UrlNotValidException("Provide valid URL");
        }

    return true;
    }
}
