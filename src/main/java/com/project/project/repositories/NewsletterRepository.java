package com.project.project.repositories;

import com.project.project.models.Invitation;
import com.project.project.models.Newsletter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsletterRepository extends CrudRepository<Newsletter, Long> {

    Newsletter findByEmail(String email);
}
