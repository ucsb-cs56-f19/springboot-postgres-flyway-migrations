package com.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.sql.DataSource;


import com.interfaces.PersonInterface;
import com.models.Person;

//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate; //this one autoconfigures and won't require bean for jdbctemplate constructor
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Repository("postgres")
public class postgreSQL implements PersonInterface {

    private final JdbcTemplate jdbcTemplate;
    private static List<Person> DB = new ArrayList<>();
    
    @Autowired
    public postgreSQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
    public List<Person> SelectDBPeople() {
        
        final String sql = "SELECT id, name, email FROM person";
        
        //SQL statement, row mapper
        //Row mapper takes values from DB and transform it into java Object
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new Person(
                    UUID.fromString(rs.getString("id")), 
                    rs.getString("name"), 
                    rs.getString("email"));
        });
    }

    @Override
    public List<Person> SelectAllLocalPeople() {
        return DB;
    }
    
    @Override
    public List<Person> SelectAllPeople() {
        List<Person> temp = SelectDBPeople();
        temp.addAll(DB);
        return temp;
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
    public int deletePersonById(UUID id) {
        Optional<Person> temp = selectPersonById(id);
        if (temp.isEmpty())
            return 0; //not existent, return 0 for unsuccessful
        DB.remove(temp.get());
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

/* STUB CODE
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
    
    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }
*/