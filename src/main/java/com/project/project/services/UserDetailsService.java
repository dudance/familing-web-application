package com.project.project.services;

import com.project.project.dto.UserDetailsDto;
import com.project.project.models.*;
import com.project.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsService {

    @Autowired
    private FilesStorageServiceImpl filesStorageService;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    UserService userService;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    ChronicleRepository chronicleRepository;

    @Autowired
    FamilyRepository familyRepository;


    public UserDetails getUserDetailsByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(value -> userDetailsRepository.findUserDetailsById(value.getUserDetailsId().getId())).orElse(null);
    }


    public UserDetails getUserDetailsByUserEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return userDetailsRepository.findUserDetailsById(user.getUserDetailsId().getId());
    }


    public UserDetails updateUserDetails(Long userDetailsId, UserDetailsDto userDetailsDto) {
        UserDetails userDetails = userDetailsRepository.findUserDetailsById(userDetailsId);
        userDetails.setName(userDetailsDto.getName());
        userDetails.setSurname(userDetailsDto.getSurname());
        userDetails.setPhone(userDetailsDto.getPhone());

        return userDetailsRepository.save(userDetails);
    }

    public String updateCurrentUserImage(MultipartFile image) {
        User user = userRepository.findUserByEmail(userService.getCurrentUserEmail());
        File file = filesStorageService.save(image);
        file.setUserId(user);
        fileRepository.save(file);
        UserDetails userDetails = userDetailsRepository.getUSerDetailsByUserId(user.getId());
        userDetails.setImage(file.getFileUrl());
        userDetailsRepository.save(userDetails);
        return file.getFileUrl();
    }

    public Set<String> getSurnamesOfFamily(Long familyId) {
        Set<String> results = new HashSet<>();
        Set<UserDetails> userDetailsOfFamily = userDetailsRepository.getUserDetailsFromFamily(familyId);
        for (UserDetails singleDetails : userDetailsOfFamily) {
            if (singleDetails.getSurname() != null) {
                results.add(singleDetails.getSurname());
            }
        }
        return results;
    }

    public Set<UserDetails> getUsersWhoSentRequest(Long familyId) {
        Set<UserDetails> resultSetOfUsers = new HashSet<>();
        List<Invitation> lisOfRequests = invitationRepository.getRequestsOfFamily(familyId);
        for (Invitation request : lisOfRequests) {
            resultSetOfUsers.add(userDetailsRepository.getUSerDetailsByUserId(request.getUserId().getId()));
        }
        return resultSetOfUsers;
    }

    public Set<UserDetails> getOwnersOfAllFamilyChronicles(Long familyId) {
        Set<UserDetails> resultSetOfUsers = new HashSet<>();
        Set<Chronicle> chroniclesSet = chronicleRepository.findAllByFamilyId(familyRepository.findById(familyId).get());
        for (Chronicle chronicle : chroniclesSet) {
            resultSetOfUsers.add(userDetailsRepository.getUSerDetailsByUserId(chronicle.getAuthorId().getId()));
        }
        return resultSetOfUsers;


    }

    public UserDetails getOwnerOfChronicle(Long chronicleId) {
        Chronicle chronicle = chronicleRepository.findById(chronicleId).get();
        User user = userRepository.findById(chronicle.getAuthorId().getId()).get();
        return userDetailsRepository.getUSerDetailsByUserId(user.getId());
    }
}
