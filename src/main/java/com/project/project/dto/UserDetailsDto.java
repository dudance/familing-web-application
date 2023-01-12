package com.project.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
public class UserDetailsDto implements Serializable {
    @NotNull(message = "id is required field")
    private Long id;
    @NotNull(message = "NAME is required field")
    private String name;
    @NotNull(message = "SURNAME is required field")
    private String surname;
    @NotNull(message = "IMAGE is required field")
    private String image;
    private String phone;
}
