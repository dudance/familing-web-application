package com.project.project.repositories;

import com.project.project.models.Gallery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends CrudRepository<Gallery, Long> {

    List<Gallery> findAllByFamilyId(Long familyId);
}
