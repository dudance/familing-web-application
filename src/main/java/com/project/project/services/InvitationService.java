package com.project.project.services;


import com.project.project.commons.enums.InvitationTypeEnum;
import com.project.project.dto.InvitationDto;
import com.project.project.mappers.InvitationMapper;
import com.project.project.models.Invitation;
import com.project.project.models.User;
import com.project.project.models.UserDetails;
import com.project.project.models.UserFamily;
import com.project.project.repositories.InvitationRepository;
import com.project.project.repositories.UserDetailsRepository;
import com.project.project.repositories.UserFamilyRepository;
import com.project.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvitationMapper invitationMapper;

    @Autowired
    private UserFamilyRepository userFamilyRepository;



    public List<Invitation> getInvitationsToUser(Long userId) {
        return invitationRepository.getInvitationsOfUser(userId, InvitationTypeEnum.INVITATION.getType());
    }

    public List<Invitation> getInvitationsFromUser(Long userId) {
        return invitationRepository.getInvitationsOfUser(userId, InvitationTypeEnum.INVITATION.getType());
    }

    public boolean isInvitationAlreadyExists(Invitation invitation) {
        return invitationRepository.getExistingInvitation(invitation.getUserId().getId(), invitation.getFamilyId().getId(), invitation.getType()) != null;
    }

    public List<Invitation> getInvitationsOfUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return invitationRepository.getInvitationsOfUser(user.getId(), InvitationTypeEnum.INVITATION.getType());

    }

    @Transactional
    public boolean addInvitation(InvitationDto invitationDto) {
        if (invitationDto.getType().equals(InvitationTypeEnum.REQUEST.getType())) {
            User currentUser = userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            invitationDto.setUserId(currentUser.getId());
            Invitation invitation = invitationMapper.dtoToObject(invitationDto);
            if (userFamilyRepository.isUserMemberOfFamily(invitationDto.getFamilyId(), invitationDto.getUserId())) {
                return false;
            }
            if (!isInvitationAlreadyExists(invitation)) {
                Invitation oppositeInvitation = invitationRepository.getOppositeInvitationIfExists(invitation.getUserId().getId(), invitation.getFamilyId().getId(), invitation.getType());
                if (oppositeInvitation != null) {
                    userFamilyRepository.save(new UserFamily(null, currentUser, oppositeInvitation.getFamilyId()));
                    invitationRepository.delete(oppositeInvitation);
                    return true;
                } else {
                    try {
                        invitationRepository.save(invitation);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (invitationDto.getType().equals(InvitationTypeEnum.INVITATION.getType())) {
            Invitation invitation = invitationMapper.dtoToObject(invitationDto);
            if (userFamilyRepository.isUserMemberOfFamily(invitationDto.getFamilyId(), invitationDto.getUserId())) {
                return false;
            }
            if (!isInvitationAlreadyExists(invitation)) {
                Invitation oppositeInvitation = invitationRepository.getOppositeInvitationIfExists(invitation.getUserId().getId(), invitation.getFamilyId().getId(), invitation.getType());
                if (oppositeInvitation != null) {
                    userFamilyRepository.save(new UserFamily(null, invitation.getUserId(), oppositeInvitation.getFamilyId()));
                    invitationRepository.delete(oppositeInvitation);
                    return true;
                } else {
                    try {
                        invitationRepository.save(invitation);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void declineInvitation(InvitationDto invitationDto) {
        User currentUser = userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        invitationDto.setUserId(currentUser.getId());
        Invitation invitation = invitationRepository.getExistingInvitation(invitationDto.getUserId(), invitationDto.getFamilyId(), invitationDto.getType());
        invitationRepository.delete(invitation);
    }


    @Transactional
    public void acceptRequest(Long familyId, Long userId) throws Exception {
        Invitation invitation = invitationRepository.getExistingInvitation(userId, familyId, InvitationTypeEnum.REQUEST.getType());
        if (invitation == null) {
            throw new Exception("cannot find request");
        } else {
            userFamilyRepository.save(new UserFamily(null, invitation.getUserId(), invitation.getFamilyId()));
            invitationRepository.delete(invitation);
        }

    }

    public void declineRequest(Long familyId, Long userId) throws Exception {
        Invitation invitation = invitationRepository.getExistingInvitation(userId, familyId, InvitationTypeEnum.REQUEST.getType());
        if (invitation == null) {
            throw new Exception("cannot find request");
        } else {
            invitationRepository.delete(invitation);
        }
    }

}
