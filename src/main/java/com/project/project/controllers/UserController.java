package com.project.project.controllers;

import com.project.project.dto.InvitationDto;
import com.project.project.dto.UserDetailsDto;
import com.project.project.dto.UserDto;
import com.project.project.exceptions.EmailAlreadyExistsException;
import com.project.project.mappers.UserMapper;
import com.project.project.models.Invitation;
import com.project.project.models.RegisterModel;
import com.project.project.models.User;
import com.project.project.repositories.FamilyRepository;
import com.project.project.repositories.UserDetailsRepository;
import com.project.project.repositories.UserFamilyRepository;
import com.project.project.repositories.UserRepository;
import com.project.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin()
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    UserFamilyRepository userFamilyRepository;

    @Autowired
    FamilyRepository familyRepository;



    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User registerNewUser(@RequestBody RegisterModel registerModel) throws EmailAlreadyExistsException {
        return userService.registerNewUser(registerModel);
    }
    @RequestMapping(value = "/allUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return (List<User>) this.userRepository.findAll();
    }

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCurrentUserEmail() {
        return userService.getCurrentUserEmail();
    }

    @RequestMapping(value = "/currentUserId/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getUserIdByEmail(@PathVariable("email") String email) {
        return userService.getUserIdByEmail(email);
    }

    @RequestMapping(value = "/user/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByEmail(@PathVariable("email") String email) {
        return userRepository.findByEmail(email);
    }


    @RequestMapping(value = "/getMembers/{familyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<User> getMembersOfFamily(@PathVariable("familyId") Long familyId) {
        return userRepository.getAllMembersOfFamily(familyId);
    }

}
