package com.project.project.repositories;

import com.project.project.models.Family;
import com.project.project.models.User;
import com.project.project.models.UserFamily;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserFamilyRepository extends CrudRepository<UserFamily, Long> {

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(uf) > 0 THEN true ELSE false END from user_family uf" +
            " where uf.family_id = :familyId and uf.user_id = :userId")
    boolean isUserMemberOfFamily (@Param("familyId") Long familyId, @Param("userId") Long userId);
}
