package com.pbx.phonebook.controller;

import com.pbx.phonebook.dto.Person;
import com.pbx.phonebook.exception.ResourceNotFoundException;
import com.pbx.phonebook.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    private List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId) throws ResourceNotFoundException {
        Person person = getPersonByIdOrThrowException(personId);

        return ResponseEntity.ok().body(person);
    }

    @GetMapping("/persons/phone-number/{phoneNumber}")
    public List<Person> getPersonsByPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber) {
        return personService.findPersonsByPhoneNumber(phoneNumber);
    }

    @PostMapping("/persons]")
    public Person createPerson(@Valid @RequestBody Person person) {
        return personService.save(person);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId, @Valid @RequestBody Person personDetails) throws ResourceNotFoundException {

        Person person = getPersonByIdOrThrowException(personId);
        person = personService.setAllPersonFields(person, personDetails);
        final Person updatedPerson = personService.save(person);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/person/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId) throws Exception {

        Person person = getPersonByIdOrThrowException(personId);

        personService.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    private Person getPersonByIdOrThrowException(Long personId) throws ResourceNotFoundException {
        return personService
                .findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found on: " + personId));
    }
}



