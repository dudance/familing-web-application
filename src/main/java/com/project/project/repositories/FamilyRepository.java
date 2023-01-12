package com.project.project.repositories;

import com.project.project.models.Family;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FamilyRepository extends CrudRepository<Family, Long> {


    @Query(nativeQuery = true, value = "SELECT * from FAMILY f " +
            "INNER JOIN USER_FAMILY uf on f.ID = uf.FAMILY_ID " +
            "INNER JOIN APP_USER u ON u.ID = uf.USER_ID " +
            "INNER JOIN USER_DETAILS ud ON u.USER_DETAILS_ID = ud.ID " +
            "WHERE LOWER(ud.surname) IN(:surnames)")
    List<Family> getAllFamiliesBySurnames(@Param("surnames") List<String> surnames);

    @Query(nativeQuery = true, value = "SELECT * from FAMILY f " +
            "INNER JOIN USER_FAMILY uf on f.ID = uf.FAMILY_ID " +
            "INNER JOIN APP_USER u ON u.ID = uf.USER_ID " +
            "WHERE u.id IN(:userId)")
    Set<Family> getAllFamiliesByUserId(@Param("userId") Long userId);


    List<Family> getAllFamiliesByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * from FAMILY f " +
            "INNER JOIN USER_FAMILY uf on f.ID = uf.FAMILY_ID " +
            "INNER JOIN APP_USER u ON u.ID = uf.USER_ID " +
            "WHERE u.id IN(:userId) AND f.name = :name")
    List<Family> getAllFamiliesOfUserByName(@Param("name") String name, @Param("userId") Long userId);

}
