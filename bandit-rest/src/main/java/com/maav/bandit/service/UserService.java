package com.maav.bandit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.maav.bandit.domain.User;
import com.maav.bandit.repository.UserRepository;
import com.maav.bandit.security.SecurityUtils;

import javax.inject.Inject;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserRepository userRepository;


    public User createUserInformation(String username, String password, String firstName, String lastName, String email) {

        User newUser = new User();
        //password should be encrypted before being assigned
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setUsername(username);
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public void changePassword(String password) {
        userRepository.findOneByUsername(SecurityUtils.getCurrentUserName()).ifPresent(u -> {
            //password should be encrypted before being assigned
            String encryptedPassword = passwordEncoder.encode(password);
            u.setPassword(encryptedPassword);
            userRepository.save(u);
        });
    }

    public User getCurrentUser() {
        return userRepository.findOneByUsername(SecurityUtils.getCurrentUserName()).get();
    }
}
