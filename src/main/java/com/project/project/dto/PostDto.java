package com.project.project.dto;

import com.project.project.commons.annotations.DiscussionFKConstraint;
import com.project.project.commons.annotations.UserFKConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PostDto implements Serializable {
    @NotNull(message = "id is required field")
    private Long id;
    @UserFKConstraint
    private Long ownerId;
    @DiscussionFKConstraint
    private Long discussionId;
    @NotNull(message = "date is required field")
    private Date date;
    @NotNull(message = "content is required field")
    private String content;

}
