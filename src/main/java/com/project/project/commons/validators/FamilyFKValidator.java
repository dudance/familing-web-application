package com.project.project.commons.validators;

import com.project.project.commons.annotations.FamilyFKConstraint;
import com.project.project.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FamilyFKValidator implements ConstraintValidator<FamilyFKConstraint, Long> {

    @Autowired
    FamilyRepository familyRepository;

    FamilyFKConstraint familyFKConstraint;

    @Override
    public void initialize(FamilyFKConstraint constraintAnnotation) {
        this.familyFKConstraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return (familyFKConstraint.nullable() && value == null || value != null && familyRepository.existsById(value));
    }
}
