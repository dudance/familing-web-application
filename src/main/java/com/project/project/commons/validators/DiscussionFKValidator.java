package com.project.project.commons.validators;

import com.project.project.commons.annotations.DiscussionFKConstraint;
import com.project.project.repositories.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DiscussionFKValidator implements ConstraintValidator<DiscussionFKConstraint, Long> {

    @Autowired
    DiscussionRepository discussionRepository;

    DiscussionFKConstraint discussionFKConstraint;

    @Override
    public void initialize(DiscussionFKConstraint constraintAnnotation) {
        this.discussionFKConstraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return (discussionFKConstraint.nullable() && value == null || value != null && discussionRepository.existsById(value));
    }
}
