package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {

    private String message;

    //constructor
    public HelloWorldBean(String message) {
        this.message=message;
    }

    //setter
    public void setMessage(String message) {
        this.message = message;
    }

    //tostring to return the spring representation of this bean object
    @Override
    public String toString() {
        return "HelloWorldBean [message=" + message + "]";
    }

    //getter to avoid conversion errors
    public String getMessage() {
        return message;
    }
}
