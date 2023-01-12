package com.project.project;

import com.google.gson.Gson;
import com.project.project.models.RegisterModel;
import com.project.project.models.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import java.net.URI;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProjectApplicationTests {

    @Test
    public void RegisterNewUserTest() {
        RegisterModel model = new RegisterModel();
        model.setEmail("email@email.pl");
        model.setName("Adam");
        model.setSurname("Kowalski");
        model.setPassword("examplePassword");

        User user = new RestTemplate().postForObject("http://localhost:8080/register", model, User.class);

        assert user != null;
        Assert.assertEquals("email@email.pl", user.getEmail());
        Assert.assertEquals("Adam", user.getUserDetailsId().getName());
        Assert.assertEquals("Kowalski", user.getUserDetailsId().getSurname());

    }
}


