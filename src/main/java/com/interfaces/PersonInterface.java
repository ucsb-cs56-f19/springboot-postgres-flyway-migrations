package com.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.models.Person;

//PersonInterface == PersonDao in the tutorial
public interface PersonInterface {
    //define the contract for anyone who wishes to implement this interface
    //Can use dependency injection to switch between implementations
    
    int insertPerson(UUID id, Person person); //this allows specification of UUID

    default int insertPerson(Person person) {
        //this allows us to generate UUID during function call
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
        //return 1 = successful. 
        //We don't check for success in interface.
    }

    //NOTE: UUID are 128 bytes, so you can't cast to int.
    //do thisUUID.equals(UUID other) for comparison
    
    List<Person> SelectAllPeople();

    int updatePersonById(UUID id, Person person); //return 1 == successful

    int deletePersonById(UUID id); //return 1 == successful

    Optional<Person> selectPersonById(UUID id);
    //Optional has isPresent() method to show if object exists
}