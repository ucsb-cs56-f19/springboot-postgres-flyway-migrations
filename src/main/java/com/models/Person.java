package com.models;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person{
    private final UUID id; // Final == can't be overidden by inheritance

    @NotBlank //you can have a null string vs a non-null but blank string
    //Check against name being blank, we want it to always be populated
    private String name;
    private String email;

    //sorting with UUID is complicated, let's just skip sorting

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        if (this.getID() == person.getID()) {
            if (this.getName() == person.getName()) {
                if (this.getEmail() == person.getEmail())
                    return true;
            }
            return false;
        }
        return false;
    }
    
    //@JsonProperty tells us when we receive a JSON POST request, it'll associate
    //those JSON attributes w/ what we tagged with @JsonProperty
    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name,@JsonProperty("email") String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UUID getID() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
