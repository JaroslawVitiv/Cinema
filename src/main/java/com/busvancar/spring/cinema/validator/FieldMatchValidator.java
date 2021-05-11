package com.busvancar.spring.cinema.validator;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>{
    private String password;
    private String confirmPassword;

    @Override
    public void initialize(final FieldMatch constraintAnnotation)
    {
    	password = constraintAnnotation.first();
    	confirmPassword = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
        try
        {
            final Object firstObj = BeanUtils.getProperty(value, password);
            final Object secondObj = BeanUtils.getProperty(value, confirmPassword);

            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (final Exception ignore)
        {
            // ignore
        }
        return true;
    }
    
}
