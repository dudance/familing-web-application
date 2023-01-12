package com.project.project.dto;

import com.project.project.commons.annotations.FamilyFKConstraint;
import com.project.project.commons.annotations.GalleryFKConstraint;
import com.project.project.commons.annotations.UserFKConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class FileDto implements Serializable {
    @NotNull(message = "id is required field")
    private Long id;
    @GalleryFKConstraint(nullable = true)
    private Long galleryId;
    @FamilyFKConstraint(nullable = true)
    private Long familyId;
    @UserFKConstraint(nullable = true)
    private Long userId;
    @NotNull(message = "fileUrl is required field")
    private String fileUrl;
    @NotNull(message = "fileType is required field")
    private String fileType;
}
