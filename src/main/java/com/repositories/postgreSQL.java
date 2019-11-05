package com.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Autowired
    public postgreSQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> SelectAllPeople() {
        
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