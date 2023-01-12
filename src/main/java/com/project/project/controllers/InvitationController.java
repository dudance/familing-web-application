package com.project.project.controllers;


import com.project.project.dto.InvitationDto;
import com.project.project.mappers.InvitationMapper;
import com.project.project.repositories.InvitationRepository;
import com.project.project.services.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invitation")
@CrossOrigin()
public class InvitationController {

    @Autowired
    private InvitationMapper invitationMapper;

    @Autowired
    private InvitationService invitationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendInvitationRequest(@RequestBody InvitationDto invitationDto) {
        if (invitationService.addInvitation(invitationDto)) {
            return ResponseEntity.status(HttpStatus.OK).body("{\"response\": \"status ok\"}");
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body("HTTP Status FOUND");
        }

    }

    @RequestMapping(value = "/invitations/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InvitationDto> getInvitationsOfUserByEmail(@PathVariable("email") String email) {
        return invitationMapper.objectToDto(invitationService.getInvitationsOfUserByEmail(email));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> declineInvitation(@RequestBody InvitationDto invitationDto) {
        try {
            invitationService.declineInvitation(invitationDto);
            return ResponseEntity.status(HttpStatus.OK).body("HTTP Status OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HTTP Status ERROR");
        }

    }

    @RequestMapping(value = "/request/delete/{familyId}/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> declineRequest(@PathVariable("familyId") Long familyId, @PathVariable("userId") Long userId) {
        try {
            invitationService.declineRequest(familyId, userId);
            return ResponseEntity.status(HttpStatus.OK).body("HTTP Status OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HTTP Status ERROR");
        }

    }

    @RequestMapping(value = "/request/create/{familyId}/{userId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> acceptRequest(@PathVariable("familyId") Long familyId,
                                                @PathVariable("userId") Long userId) {
        try {
            invitationService.acceptRequest(familyId, userId);
            return ResponseEntity.status(HttpStatus.OK).body("{\"result\": \"HTTP Status OK\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HTTP Status ERROR");
        }

    }


}
