package com.project.project.commons.validators;


import com.project.project.commons.annotations.UserFKConstraint;
import com.project.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserFKValidator implements ConstraintValidator<UserFKConstraint, Long> {

    @Autowired
    UserRepository userRepository;

    UserFKConstraint userFKConstraint;

    @Override
    public void initialize(UserFKConstraint constraintAnnotation) {
        this.userFKConstraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return (userFKConstraint.nullable() && value == null || value != null && userRepository.existsById(value));
    }
}
