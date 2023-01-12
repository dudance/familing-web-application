package com.project.project.controllers.websocket;

import com.project.project.mappers.UserMapper;
import com.project.project.models.Discussion;
import com.project.project.models.Post;
import com.project.project.repositories.DiscussionRepository;
import com.project.project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserMapper userMapper;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to , Post message) {
        System.out.println("handling send message: " + message + " to: " + to);
        message.setDiscussionId(createAndOrGetChat(to));
        message.setDate(Timestamp.from(Instant.now()));
        message = postRepository.save(message);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }

    @PostMapping("/getMessages")
    public List<Post> getMessages(@RequestBody String chat) {
        Discussion ce = discussionRepository.findByName(chat);

        if(ce != null) {
            return postRepository.findAllByDiscussionId(ce);
        }
        else{
            return new ArrayList<>();
        }
    }

    private Discussion createAndOrGetChat(String name) {
        Discussion ce = discussionRepository.findByName(name);

        if (ce != null) {
            return ce;
        }
        else {
            Discussion newChat = new Discussion();
            newChat.setName(name);
            return discussionRepository.save(newChat);
        }
    }



}
