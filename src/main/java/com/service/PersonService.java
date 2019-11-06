package com.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.interfaces.PersonInterface;
import com.models.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    private final PersonInterface person_i;

    //Since you can have multiple implementations of the interface,
    //We have a qualifier, i.e. an identifier
    //Dependency injection resolver by calling the necessary beans
    //We wire it to PersonDB repository, but in the future, we can wire to mongo, etc.
    //TLDR: we can have multiple DB's, but qualifier allows us to select which one to use as our service
    @Autowired
    public PersonService(@Qualifier("postgres") PersonInterface person_i) {
        this.person_i = person_i;
    }

    //From the above, using @Qualifier("db_example"), we have already instantiated
    //the PersonDB class as a bean. Therefore, we can call this, and it'll 
    //utilize the peronDB class's overridden insertPerson and insert to its ArrayList
    //Dependency injection! yay!
    public int addPerson(Person person) {
        return person_i.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return person_i.SelectAllPeople();
        //remember, this method is defined in our DB bean
    }

    public Optional<Person> getPersonById(UUID id) {
        return person_i.selectPersonById(id);
    }

    public int updatePerson(UUID id, Person person) {
        return person_i.updatePersonById(id, person);
    }

    public int deletePersonById(UUID id) {
        return person_i.deletePersonById(id);
    }

    public List<Person> getAllLocalPeople() {
        return person_i.SelectAllLocalPeople();

    }
}