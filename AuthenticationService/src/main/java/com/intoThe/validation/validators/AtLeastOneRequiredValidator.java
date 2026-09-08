package com.intoThe.validation.validators;

import com.intoThe.validation.annoation.AtLeastOneRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class AtLeastOneRequiredValidator implements ConstraintValidator<AtLeastOneRequired,Object> {

    @Override
    public boolean isValid(Object data, ConstraintValidatorContext validatorContext){
            boolean isValid = false;
        try{

            Field emailId = data.getClass().getDeclaredField("userEmail");
            Field mobileNumber = data.getClass().getDeclaredField("mobileNumber");
            emailId.setAccessible(true);
            mobileNumber.setAccessible(true);
            String emailIdValue = (String) emailId.get(data);
            String mobileNumberValue = (String)  mobileNumber.get(data);
            isValid =   (StringUtils.hasText(emailIdValue) || StringUtils.hasText(mobileNumberValue));

        }catch (Exception e){

        }
        return isValid;
    }
}
