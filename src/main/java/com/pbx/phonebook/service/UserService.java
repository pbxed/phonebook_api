package com.pbx.phonebook.service;

import com.pbx.phonebook.dto.User;
import com.pbx.phonebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User setAllUserFields(User user, @Valid User userDetails) {
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setUpdatedAt(new Date());

        return user;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> findUsersByPhoneNumber(String phoneNumber) {
        return userRepository.findUsersByPhoneNumber(phoneNumber);
    }
}
