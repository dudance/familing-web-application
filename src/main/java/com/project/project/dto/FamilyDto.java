package com.project.project.dto;

import com.project.project.commons.annotations.DiscussionFKConstraint;
import com.project.project.commons.annotations.GalleryFKConstraint;
import com.project.project.commons.annotations.UserFKConstraint;
import com.project.project.models.Gallery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
public class FamilyDto implements Serializable {
    @NotNull(message = "id is required field")
    private Long id;
    @NotNull(message = "name is required field")
    private String name;
    @UserFKConstraint
    private Long ownerId;
    @NotNull(message = "IMAGE is required field")
    private String image;
    @NotNull(message = "description is required field")
    private String description;
}
