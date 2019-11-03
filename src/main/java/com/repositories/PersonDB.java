package com.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.models.Person;

import org.springframework.stereotype.Repository;

import com.interfaces.PersonInterface;


//Annotations to tell that this class needs to be instantiated as a bean
//As a bean, we can inject it into other classes
//We can use either @component or @repository but @repository serves class as a repository
@Repository("db_example")
public class PersonDB implements PersonInterface {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        for (Person i: DB) { //see if UUID id exists first
            if (i.getID().equals(id))
                return 0;
        }
        DB.add(new Person(id, person.getName(), person.getEmail()));
        return 1; //1 means successfully inserted
    }

    @Override
    public List<Person> SelectAllPeople() {
        return DB;

    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> temp = selectPersonById(id);
        if (temp.isEmpty())
            return 0; //not existent, return 0 for unsuccessful
        DB.remove(temp.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        Optional<Person> temp = selectPersonById(id);
        if (temp.isEmpty())
            return 0;
        //DB.get(DB.indexOf(temp.get())).setEmail(person.getEmail());
        //DB.get(DB.indexOf(temp.get())).setName(person.getName());
        DB.set(DB.indexOf(temp.get()), new Person(id, person.getName(), person.getEmail()));
        return 1;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream() // Java stream functionality
                .filter(person -> person.getID().equals(id))
                // Lambda expression for a Person person object which returns a bool
                .findFirst();

    }

}