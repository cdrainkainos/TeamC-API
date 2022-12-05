package com.kainos.ea.validator;

import com.kainos.ea.exception.validation.UrlNotValidException;
import com.kainos.ea.model.JobRoleXL;
import java.util.regex.Pattern;

public class JobRoleValidator {

    public boolean isValidJobRole(JobRoleXL jobRoleXL) throws UrlNotValidException {

        if (jobRoleXL.getBandId() > 255 || jobRoleXL.getBandId()<=0){
            return false;
        }

        if (jobRoleXL.getJobFamilyId() > 255 || jobRoleXL.getJobFamilyId() <=0){
            return false;
        }

        if (jobRoleXL.getRole_title().length() > 35){
            return false;
        }

        if (jobRoleXL.getJobSpecification().length() > 255){
            return false;
        }

        String linkRegex = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";
        Pattern pattern = Pattern.compile(linkRegex);
        if (!pattern.matcher(jobRoleXL.getJobSpecLink()).matches()){
            System.out.println(jobRoleXL.getJobSpecLink());
            throw new UrlNotValidException("Provide valid URL");
        }

    return true;
    }
}
