package com.project.project.commons.annotations;


import com.project.project.commons.validators.UserDetailsFKValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserDetailsFKValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserDetailsFKConstraint {

    String message() default "userDetails don't exist";

    boolean nullable() default false;
}
