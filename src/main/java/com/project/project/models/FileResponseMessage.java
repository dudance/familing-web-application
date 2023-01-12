package com.project.project.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class FileResponseMessage {
    @NotNull(message = "message is required field")
    private String message;
}
