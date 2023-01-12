package com.project.project.repositories;

import com.project.project.models.Discussion;
import com.project.project.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends CrudRepository<Discussion, Long> {
    Discussion findByName(String name);

}
