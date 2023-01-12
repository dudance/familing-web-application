package com.project.project.commons.annotations;

import com.project.project.commons.validators.FamilyFKValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FamilyFKValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FamilyFKConstraint {

    String message() default "family doesn't exist";

    boolean nullable() default false;

}
