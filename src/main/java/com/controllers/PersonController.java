package com.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Null;

import com.models.Person;
import com.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//This controller will be mainly be used for updating/fetching person records

@RequestMapping("user") //endpoint
@RestController //RestController class
public class PersonController {

    //The service interacts with the bean PersonDB,
    //Controller handles user requests and interacts with our service
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //GET is for retrieving data from server (resource info shown in URL)
    //POST is to update server (hidden/sensitive info not shown)
    //@RequestBody turns the Json object (the 'body') that we receive from POST into a person object
    //We're able to do this because Person variable are configured with JsonProperty attributes so it maps to those
    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        //From our implementation, UUID is always generated our program
        //We don't really need a @JsonProperty for UUID id
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
        //getAllPeople() -> personService -> person_i.SelectAllPeople() -> personDB's override
    }
    //We will be using Postman (desktop tool) to act as client

    @GetMapping(path = "{id}") //same as /user/the_id
    public Person getPersonById(@PathVariable("id") UUID id) { //PathVariable refers to the id in URL ~/user/id
        return personService.getPersonById(id)
        .orElse(null); //exception, default to null for now (return nothing)
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePersonById(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @RequestBody Person person) {
        personService.updatePerson(id, person);
    }
}

//Basic coverage of REST API:
/*
-GET for requesting data
-POST to push to DB
-Delete
-Put for updating existing user
*/