package com.project.project.commons.annotations;

import com.project.project.commons.validators.DiscussionFKValidator;
import com.project.project.commons.validators.FamilyFKValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DiscussionFKValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DiscussionFKConstraint {
    String message() default "discussion doesn't exist";

    boolean nullable() default false;
}
