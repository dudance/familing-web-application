package com.project.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class FileInfo {
    @NotNull(message = "name is required field")
    private String name;
    @NotNull(message = "url is required field")
    private String url;
}
