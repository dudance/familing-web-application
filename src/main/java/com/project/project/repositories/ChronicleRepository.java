package com.project.project.repositories;

import com.project.project.models.Chronicle;
import com.project.project.models.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ChronicleRepository extends CrudRepository<Chronicle, Long> {

    Optional<Chronicle> findById(Long id);

    Set<Chronicle> findAllByFamilyId(Family familyId);

}
