package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

//We want it to be managed by spring(using @Component) as it'll be talking to DB,
//we could put repository as well but here, we're using a simple arraylist to store list of users
@Component
public class UserDaoService {
    private static List<User> users=new ArrayList<>();

    private static int usersCount=3;
    static{
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }
    //We need to return a specific user(find), all the users(findAll), 
    //should be able to add new user(save)
    public List<User> findAll(){
        return users;
    }
    //Ideally when new user is created, ID(primary key) is determined by backend/DB
    public User save(User user){
        if(user.getId()==null){       //If it doesnt have ID, give an ID
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }
    public User findOne(int id){
        for(User user:users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }
}