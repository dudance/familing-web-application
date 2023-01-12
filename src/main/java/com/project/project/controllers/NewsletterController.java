package com.project.project.controllers;

import com.project.project.models.Newsletter;
import com.project.project.services.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newsletter")
@CrossOrigin()
public class NewsletterController {

    @Autowired
    private NewsletterService newsletterService;


    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Newsletter addEmailToNewsletter(@RequestBody Newsletter newsletter) throws Exception {
        return newsletterService.addNewEmailToNewsletter(newsletter);
    }

}
