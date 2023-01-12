package com.project.project.services;

import com.project.project.exceptions.EmailAlreadyExistsException;
import com.project.project.models.RegisterModel;
import com.project.project.models.User;
import com.project.project.models.UserDetails;
import com.project.project.repositories.UserDetailsRepository;
import com.project.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(RegisterModel registerModel) throws EmailAlreadyExistsException {
        if (userRepository.findUserByEmail(registerModel.getEmail()) != null) {
            throw new EmailAlreadyExistsException("User with that email already exists");
        } else {
            UserDetails userDetails = new UserDetails();
            userDetails.setName(registerModel.getName());
            userDetails.setSurname(registerModel.getSurname());
            userDetails.setImage("default_user_icon.svg");
            userDetails = userDetailsRepository.save(userDetails);
            User user = new User();
            user.setEmail(registerModel.getEmail());
            user.setPassword(passwordEncoder.encode(registerModel.getPassword()));
            user.setUserDetailsId(userDetails);
            return userRepository.save(user);
        }
    }

    public String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Long getUserIdByEmail(String email) {
        return userRepository.findUserByEmail(email).getId();
    }

}
