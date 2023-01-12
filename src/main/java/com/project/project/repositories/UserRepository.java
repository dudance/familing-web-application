package com.project.project.repositories;

import com.project.project.models.Family;
import com.project.project.models.User;
import com.project.project.models.UserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);
    User findByEmail(String email);
    User findUserByUserDetailsId(UserDetails userDetailsId);


    @Query(nativeQuery = true, value = "SELECT * from APP_USER au " +
            "INNER JOIN USER_FAMILY uf on au.ID = uf.USER_ID " +
            "INNER JOIN FAMILY f ON f.ID = uf.FAMILY_ID " +
            "WHERE f.ID = :familyId")
    Set<User> getAllMembersOfFamily(@Param("familyId") Long familyId);

}

