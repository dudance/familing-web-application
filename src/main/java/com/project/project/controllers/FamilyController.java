package com.project.project.controllers;

import com.project.project.dto.FamilyDto;
import com.project.project.dto.InvitationDto;
import com.project.project.mappers.FamilyMapper;
import com.project.project.models.Family;
import com.project.project.repositories.FamilyRepository;
import com.project.project.repositories.UserFamilyRepository;
import com.project.project.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/family")
@CrossOrigin()
public class FamilyController {

    @Autowired private FamilyMapper familyMapper;
    @Autowired private FamilyRepository familyRepository;
    @Autowired private FamilyService familyService;
    @Autowired private UserFamilyRepository userFamilyRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FamilyDto> getAllFamilies() {
        return familyMapper.objectToDto((List<Family>) familyRepository.findAll());
    }

    @RequestMapping(value="/id/{familyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FamilyDto getFamilyById(@PathVariable("familyId") Long familyId) {
        return familyMapper.objectToDto(familyRepository.findById(familyId).get());
    }

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<FamilyDto> getAllFamiliesOfCurrentUser() {
        return familyMapper.objectToDto(familyService.getAllFamiliesOfCurrentUser());
    }


    @RequestMapping(value = "/surnames/{search}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FamilyDto> getAllFamiliesBySurnames(@PathVariable("search") String search) {
        return familyMapper.objectToDto(familyService.getAllFamiliesBySurnames(search));
    }

    @RequestMapping(value = "/member/{search}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<FamilyDto> getAllFamiliesByMember(@PathVariable("search") String search) {
        String[] parameters = search.split("\\*");
        return familyMapper.objectToDto(familyService.getAllFamiliesByUserNameAndSurname(parameters[0], parameters[1]));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<FamilyDto> getAllFamiliesByUserId(@PathVariable("id") Long id) {
        return familyMapper.objectToDto(familyRepository.getAllFamiliesByUserId(id));
    }

    @RequestMapping(value = "/invitations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<FamilyDto> getFamiliesByUserInvitations(@RequestBody List<InvitationDto> invitationDtoList) {
        return familyMapper.objectToDto(familyService.getFamiliesByUserInvitations(invitationDtoList));
    }

    @RequestMapping(value = "/createFamily/updateImage/{familyId}", method = RequestMethod.POST)
    public String uploadNewFamilyImage(@RequestBody MultipartFile image, @PathVariable("familyId") Long familyId) {
        return familyService.uploadNewFamilyImage(image, familyId);
    }

    @RequestMapping(value = "/createFamily/new", method = RequestMethod.POST)
    public FamilyDto createNewFamily(@RequestBody FamilyDto familyDto) {
        return familyMapper.objectToDto(familyService.addNewFamily(familyDto));
    }


}
