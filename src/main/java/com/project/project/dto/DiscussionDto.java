package com.project.project.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class DiscussionDto implements Serializable {
    @NotNull(message = "id is required field")
    private Long id;
    @NotNull(message = "name is required field")
    private String name;
}
