package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

//import static org.springframework.hateos.mvc.ControllerLinkBuilder.*;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;
    //GET /users
    //retrieveAllUsers
    @GetMapping("/users") 
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //To show helpful links in response using hateos, EntityModel & WebMvcBuilder
    //concepts needs to be known
    //We want to send this response as part of user bean by not messing with current structure of every beans
    //So, use of EntityModel is needed. Whenever using of hateos and adding links is reqd.
    //We need to wrap the user in EntityModel


    //GET /users/{id}
    //retrieveUser(int id)
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }
        //show link for all users in same page using hateos
        // "/users" -> retrieveAllUsers
        //Create a resource around user and constructor of user
        //Resource<User> resource=new Resource<User>(user);

        //Add links to the resource, ControllerLinkBuilder enables to create links from methods
        //gives what is the link of retrieveAllUsers to linkTo
        //ControllerLinkBuilder linkTo= 
            //linkTo(methodOn(this.getClass().retrieveAllUsers()));
        //To add the link to the resource and give what it should display in hateos:
        //resource.add(linkTo.withRel("all-users"));

        //return resource which has both data and links instead of just that specific user
        //return resource;
        //return user;

        //Wrapping User class inside EntityModel to use same user response with links with help of hateos
        EntityModel<User> entityModel=EntityModel.of(user);
        //Now to add links in this entitymodel wrapped user, we need to use WebMvcLinkBuilder
        //Instead of hardcoding the link here, using the specific method and linkTo to get the link
        WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
        //WebMvcLinkBuilder link=linkTo(methodOn(null,null));
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    //output-CREATED response code & Return the created URI
    //input-details of user in terms of json format so, use RequestBody which is passed
    //@valid is present in jakarta(earlier javax) implementation 
    //Earlier javax was given by springboot starter web(starter is used to make these rest webservices)
    //But now spring-boot-starter-validation dependecy has to be added separately
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser=service.save(user);

        //return CREATED
        // return created user details as GET response /users/4

        //How to create URI of the location of the resource that is created
        //Path method allows to append to the URI
        URI location=ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId()).toUri();


        //sends location of the resource which was created
        return ResponseEntity.created(location).build();
    }

    //After deleting, sending 200 status is fine(don't return anything that is void method) 
    //or a responseentity of status No Content can also be returned
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }
    }

}