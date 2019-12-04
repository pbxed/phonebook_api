package com.pbx.phonebook.repository;

import com.pbx.phonebook.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.phoneNumber like %?1")
    List<User> findUsersByPhoneNumber(String phoneNumber);
}

