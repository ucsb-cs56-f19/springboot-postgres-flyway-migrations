package com.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.interfaces.PersonInterface;
import com.models.Person;

import org.springframework.stereotype.Repository;

@Repository("postgres")
public class postgreSQL implements PersonInterface {
    
    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> SelectAllPeople() {
        return List.of(new Person(UUID.randomUUID(), "name stub", "email@ucsb.edu"));
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return null;
    }
}