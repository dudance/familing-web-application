package com.project.project.dto;

import com.project.project.commons.annotations.FamilyFKConstraint;
import com.project.project.commons.annotations.UserFKConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class GalleryDto implements Serializable {
    @NotNull(message = "id is required field")
    private Long id;
    @FamilyFKConstraint(nullable = true)
    private Long familyId;
    @UserFKConstraint
    private Long ownerId;
    @NotNull(message = "name is required field")
    private String name;
}
