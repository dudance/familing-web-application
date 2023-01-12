package com.project.project.dto;

import com.project.project.commons.annotations.FamilyFKConstraint;
import com.project.project.commons.annotations.UserFKConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
public class UserFamilyDto implements Serializable {
    @NotNull(message = "id is required field")
    private Long id;
    @UserFKConstraint
    private Long userId;
    @FamilyFKConstraint
    private Long familyId;
}


