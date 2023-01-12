package com.project.project.controllers;

import com.project.project.dto.UserDetailsDto;
import com.project.project.mappers.UserDetailsMapper;
import com.project.project.repositories.UserDetailsRepository;
import com.project.project.repositories.UserRepository;
import com.project.project.services.UserDetailsService;
import com.project.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/userDetails")
public class UserDetailsController {

    @Autowired
    UserDetailsMapper userDetailsMapper;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/getSurnames/{familyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> getSurnamesOfFamily(@PathVariable("familyId") Long familyId) {
        return userDetailsService.getSurnamesOfFamily(familyId);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsDto getUserDetailsByUserId(@PathVariable("id") Long id) {
        return userDetailsMapper.objectToDto(userDetailsService.getUserDetailsByUserId(id));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsDto getUserDetailsById(@PathVariable("id") Long id) {
        return userDetailsMapper.objectToDto(userDetailsRepository.findUserDetailsById(id));
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsDto getUserDetailsByUserEmail(@PathVariable("email") String email) {
        return userDetailsMapper.objectToDto(userDetailsService.getUserDetailsByUserEmail(email));
    }

    @RequestMapping(value = "/updateUser/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsDto getUserDetailsByUserEmail(@RequestBody UserDetailsDto userDetailsDto) {
        Long currentUserDetailsId = userRepository.findUserByEmail(userService.getCurrentUserEmail()).getUserDetailsId().getId();
        return userDetailsMapper.objectToDto(userDetailsService.updateUserDetails(currentUserDetailsId, userDetailsDto));
    }

    @RequestMapping(value = "/usersOfRequests/{familyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<UserDetailsDto> getRequestsOfFamily(@PathVariable("familyId") Long familyId) {
        return userDetailsMapper.objectToDto(userDetailsService.getUsersWhoSentRequest(familyId));
    }

    @RequestMapping(value = "/updateUser/updateImage/", method = RequestMethod.POST)
    public String updateCurrentUserImage(@RequestBody MultipartFile image) {
        return userDetailsService.updateCurrentUserImage(image);
    }

    @RequestMapping(value = "/chronicles/{familyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<UserDetailsDto> getOwnersOfAllFamilyChronicles(@PathVariable("familyId") Long familyId) {
        return userDetailsMapper.objectToDto(userDetailsService.getOwnersOfAllFamilyChronicles(familyId));
    }

    @RequestMapping(value = "/chronicle/{chronicleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsDto getOwnerOfChronicle(@PathVariable("chronicleId") Long chronicleId) {
        return userDetailsMapper.objectToDto(userDetailsService.getOwnerOfChronicle(chronicleId));
    }


}
