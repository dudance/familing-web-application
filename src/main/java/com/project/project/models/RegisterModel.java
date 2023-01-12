package com.project.project.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterModel implements Serializable {
    private String name;
    private String surname;
    private String email;
    private String password;
}
