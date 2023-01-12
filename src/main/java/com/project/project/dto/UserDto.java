package com.project.project.dto;

import com.project.project.commons.annotations.UserDetailsFKConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Getter
@Setter
public class UserDto implements Serializable {
    @NotNull(message = "id is required field")
    private Long id;
    @UserDetailsFKConstraint
    private Long userDetailsId;
    @NotNull(message = "EMAIL is required field")
    private String email;
    @NotNull(message = "password is required field")
    private  String password;
}
