package com.project.project.commons.validators;


import com.project.project.commons.annotations.GalleryFKConstraint;
import com.project.project.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GalleryFKValidator implements ConstraintValidator<GalleryFKConstraint, Long> {

    @Autowired
    GalleryRepository galleryRepository;

    GalleryFKConstraint galleryFKConstraint;

    @Override
    public void initialize(GalleryFKConstraint constraintAnnotation) {
        this.galleryFKConstraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return (galleryFKConstraint.nullable() && value == null || value != null && galleryRepository.existsById(value));
    }
}
