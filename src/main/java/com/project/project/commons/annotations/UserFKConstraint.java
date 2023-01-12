package com.project.project.commons.annotations;

import com.project.project.commons.validators.FamilyFKValidator;
import com.project.project.commons.validators.UserFKValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserFKValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserFKConstraint {

    String message() default "user doesn't exist";

    boolean nullable() default false;
}
