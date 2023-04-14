package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {
    private Integer id;
    //Validating earlier javax, now jakarta maybe, also message can be added with the argument
    //These validations are defined in validation-api.jar
    //Most popular implementation of validation API is hibernate validator.jar
    //hibernate validator implements validations defined here
    //These 2 jars are defined in spring boot web starter dependencies
    @Size(min=2,message="Name should have atleast 2 characters")
    //Customizing display of name field in GET response
    @JsonProperty("user_name")
    private String name;
    //Validating Birthdate should be in past
    @JsonProperty("birth_date")
    @Past
    private Date birthDate;

    //Constructor
    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
    }
}
