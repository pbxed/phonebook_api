package com.pbx.phonebook.repository;

import com.pbx.phonebook.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select u from Person u where u.phoneNumber like %?1")
    List<Person> findUsersByPhoneNumber(String phoneNumber);
}

