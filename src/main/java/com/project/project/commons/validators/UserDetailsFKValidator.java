package com.project.project.commons.validators;

import com.project.project.commons.annotations.UserDetailsFKConstraint;
import com.project.project.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserDetailsFKValidator implements ConstraintValidator<UserDetailsFKConstraint, Long> {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    UserDetailsFKConstraint userDetailsFKConstraint;

    @Override
    public void initialize(UserDetailsFKConstraint constraintAnnotation) {
        this.userDetailsFKConstraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return (userDetailsFKConstraint.nullable() && value == null || value != null && userDetailsRepository.existsById(value));
    }
}
