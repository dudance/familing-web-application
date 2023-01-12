package com.project.project.repositories;

import com.project.project.models.UserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {

    UserDetails findUserDetailsById(Long id);

    @Query(nativeQuery = true, value = "SELECT * from USER_DETAILS ud " +
            "INNER JOIN APP_USER u ON u.USER_DETAILS_ID = ud.ID " +
            "INNER JOIN USER_FAMILY uf on u.ID = uf.USER_ID " +
            "INNER JOIN FAMILY f ON f.ID = uf.FAMILY_ID " +
            "WHERE f.id IN(:familyId)")
    Set<UserDetails> getUserDetailsFromFamily(@Param("familyId") Long familyId);

    @Query(nativeQuery = true, value = "SELECT * FROM USER_DETAILS ud INNER JOIN APP_USER u ON u.USER_DETAILS_ID = ud.ID WHERE u.ID = :userId ")
    UserDetails getUSerDetailsByUserId(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "SELECT * from USER_DETAILS ud WHERE LOWER(ud.name) = :name and LOWER(ud.surname) = :surname")
    List<UserDetails> findAllByNameAndSurname(@Param("name") String name, @Param("surname") String surname);


}
