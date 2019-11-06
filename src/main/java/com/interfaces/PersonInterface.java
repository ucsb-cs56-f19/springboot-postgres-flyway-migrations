package com.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.models.Person;

public interface PersonInterface {
    
    //return 1 == successful
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    
    List<Person> SelectDBPeople();

    List<Person> SelectAllLocalPeople();

    List<Person> SelectAllPeople();

    int updatePersonById(UUID id, Person person);

    int deletePersonById(UUID id); 

    Optional<Person> selectPersonById(UUID id);

}