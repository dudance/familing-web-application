package com.project.project.commons.annotations;

import com.project.project.commons.validators.FamilyFKValidator;
import com.project.project.commons.validators.GalleryFKValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GalleryFKValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GalleryFKConstraint {

    String message() default "gallery doesn't exist";

    boolean nullable() default false;
}
