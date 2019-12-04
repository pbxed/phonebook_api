package com.pbx.phonebook.controller;

import com.pbx.phonebook.dto.User;
import com.pbx.phonebook.exception.ResourceNotFoundException;
import com.pbx.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class Controller {

    private UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = getUserByIdOrThrowException(userId);

        return ResponseEntity.ok().body(user);
    }

    // Only working with exact phone number. Need to change SQL query to return partial matches.
    @GetMapping("/users/phone-number/{phoneNumber}")
    public List<User> getUsersByPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber) {
        return userService.findUsersByPhoneNumber(phoneNumber);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException {

        User user = getUserByIdOrThrowException(userId);
        user = userService.setAllUserFields(user, userDetails);
        final User updatedUser = userService.save(user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {

        User user = getUserByIdOrThrowException(userId);

        userService.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    private User getUserByIdOrThrowException(Long userId) throws ResourceNotFoundException {
        return userService
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on: " + userId));
    }
}



