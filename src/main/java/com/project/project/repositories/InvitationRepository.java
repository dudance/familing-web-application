package com.project.project.repositories;

import com.project.project.dto.InvitationDto;
import com.project.project.models.Invitation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Long> {

    List<Invitation> findAllByUserId(Long userId);

    @Query(nativeQuery = true, value = "DELETE FROM INVITATION i WHERE i.FAMILY_ID = :familyId AND i.USER_ID = :userId AND i.TYPE = :invType")
    void deleteInvitationByFamilyIdsAndType(@Param("userId") Long userId, @Param("invType") String invType, @Param("familyId") Long familyId);

    @Query(nativeQuery = true, value = "SELECT * FROM INVITATION i WHERE i.FAMILY_ID = :familyId AND i.type = 'INV_FROM'")
    List<Invitation> getRequestsOfFamily(@Param("familyId") Long familyId);


    @Query(nativeQuery = true, value = "SELECT * FROM INVITATION i WHERE i.USER_ID = :userId and i.TYPE = :invType")
    List<Invitation> getInvitationsOfUser(@Param("userId") Long userId, @Param("invType") String invType);


    @Query(nativeQuery = true, value = "SELECT * FROM INVITATION i WHERE i.USER_ID = :userId and i.TYPE = :invType and i.FAMILY_ID = :familyId")
    Invitation getExistingInvitation(@Param("userId") Long userId, @Param("familyId") Long familyId, @Param("invType") String invType);

    @Query(nativeQuery = true, value = "SELECT * FROM INVITATION i WHERE i.USER_ID = :userId and i.TYPE != :invType and i.FAMILY_ID = :familyId")
    Invitation getOppositeInvitationIfExists(@Param("userId") Long userId, @Param("familyId") Long familyId, @Param("invType") String invType);
}
