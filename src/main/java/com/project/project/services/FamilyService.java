package com.project.project.services;


import com.project.project.dto.FamilyDto;
import com.project.project.dto.InvitationDto;
import com.project.project.models.*;
import com.project.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class FamilyService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFamilyRepository userFamilyRepository;

    @Autowired
    private FilesStorageServiceImpl filesStorageService;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    public Set<Family> getAllFamiliesOfCurrentUser() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findUserByEmail(currentUserEmail);
        return familyRepository.getAllFamiliesByUserId(currentUser.getId());
    }

    public List<Family> getAllFamiliesOfCurrentUserByName(String name) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findUserByEmail(currentUserEmail);
        return familyRepository.getAllFamiliesOfUserByName(name, currentUser.getId());
    }


    public List<Family> getAllFamiliesBySurnames(String search) {
        List<String> modifiedString = List.of(search.toLowerCase().split("\\*"));
        return familyRepository.getAllFamiliesBySurnames(modifiedString);
    }
    
    public Set<Family> getAllFamiliesByUserNameAndSurname(String name, String surname) {
        List<UserDetails> listOfUsers = userDetailsRepository.findAllByNameAndSurname(name.toLowerCase(), surname.toLowerCase());

        Set<Family> resultSetOfFamilies = new HashSet<>();

        for (UserDetails ud:listOfUsers) {
            resultSetOfFamilies.addAll(familyRepository.getAllFamiliesByUserId(userRepository.findUserByUserDetailsId(ud).getId()));
        }

        return resultSetOfFamilies;
    }

    public List<Family> getAllFamiliesByInvitations(List<Invitation> listOfInvitations) {
        List<Family> resultListOfFamilies = new ArrayList<>();
        Optional<Family> tempFamily;

        for (Invitation inv : listOfInvitations) {
            tempFamily = familyRepository.findById(inv.getFamilyId().getId());
            tempFamily.ifPresent(resultListOfFamilies::add);

        }
        return resultListOfFamilies;
    }

    @Transactional
    public Family addNewFamily(FamilyDto familyDto) {
        Family newFamily = new Family();
        newFamily.setDescription(familyDto.getDescription());
        newFamily.setName(familyDto.getName());
        newFamily.setImage("default_family_icon.svg");


        newFamily.setOwnerId(userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        Family resultFamily = familyRepository.save(newFamily);
        UserFamily userFamily = new UserFamily(null, resultFamily.getOwnerId(), resultFamily);
        userFamilyRepository.save(userFamily);

        return resultFamily;
    }

    public Set<Family> getFamiliesByUserInvitations(List<InvitationDto> listOfInvitations) {
        Set<Family> resultSet = new HashSet<>();
        for (InvitationDto invitation : listOfInvitations) {
                familyRepository.findById(invitation.getFamilyId()).ifPresent(resultSet::add);
        }
        return resultSet;
    }


    public String uploadNewFamilyImage(MultipartFile image, Long familyId) {
        File file = filesStorageService.save(image);
        file.setFamilyId(familyRepository.findById(familyId).get());
        fileRepository.save(file);
        Family family;
        if (familyRepository.findById(familyId).isPresent()) {
            family = familyRepository.findById(familyId).get();
        } else {
            throw new NoSuchElementException("Cannot find family with that id");
        }
        family.setImage(file.getFileUrl());
        familyRepository.save(family);
        return file.getFileUrl();
    }
}
