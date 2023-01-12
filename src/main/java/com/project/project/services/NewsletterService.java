package com.project.project.services;

import com.project.project.models.Newsletter;
import com.project.project.repositories.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService {

    @Autowired
    private NewsletterRepository newsletterRepository;

    public Newsletter addNewEmailToNewsletter(Newsletter newsletter) throws Exception {
        if (newsletterRepository.findByEmail(newsletter.getEmail()) != null) {
            throw new Exception("email already exists in newsletters' base");
        } else {
            return newsletterRepository.save(newsletter);
        }
    }
}
