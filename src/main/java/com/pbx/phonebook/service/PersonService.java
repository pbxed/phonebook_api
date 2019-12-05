package com.pbx.phonebook.service;

import com.pbx.phonebook.dto.Person;
import com.pbx.phonebook.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long personId) {
        return personRepository.findById(personId);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person setAllPersonFields(Person person, @Valid Person personDetails) {
        person.setFirstName(personDetails.getFirstName());
        person.setLastName(personDetails.getLastName());
        person.setPhoneNumber(personDetails.getPhoneNumber());
        person.setUpdatedAt(new Date());

        return person;
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }

    public List<Person> findPersonsByPhoneNumber(String phoneNumber) {
        return personRepository.findUsersByPhoneNumber(phoneNumber);
    }
}
