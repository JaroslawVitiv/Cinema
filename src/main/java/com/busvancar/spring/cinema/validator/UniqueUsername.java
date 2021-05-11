package com.busvancar.spring.cinema.validator;

import java.lang.annotation.Target;

import javax.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Documented;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.validation.Payload;



@Target({ FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { UniqueUsernameValidator.class })
public @interface UniqueUsername {

	String message() default "{javax.validation.constraints.Size.message}";
	Class <?> [] groups() default { };
	Class<? extends Payload>[] payload() default { }; 

}