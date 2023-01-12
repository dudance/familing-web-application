package com.project.project.dto;

import com.project.project.commons.annotations.FamilyFKConstraint;
import com.project.project.commons.annotations.GalleryFKConstraint;
import com.project.project.commons.annotations.UserFKConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
public class ChronicleDto implements Serializable {
    @NotNull(message = "id is a required field")
    private Long id;
    @NotNull(message = "name is a required field")
    private String name;
    @FamilyFKConstraint
    private Long familyId;
    @UserFKConstraint
    private Long authorId;
    @NotNull(message = "date is required field")
    private Date date;
    @NotNull(message = "content is required field")
    private String content;
    @GalleryFKConstraint
    private Long galleryId;
}
