package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

//As it is sending a response back, need to make it a restcontroller
//To make it applicable across all the other controllers, by springcontrolleradvice
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler 
extends ResponseEntityExceptionHandler{
    //Need to override the following method from ResponseEntityExceptionHandler.class
    //to customize further (By default, all exceptions let keep as Internal ServerError)
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Only when the user is not found, return userNotfound exception rather than default internal server error
    //A common jar can be created for different exceptions in the organization
    //But here, we're using the exceptions available in spring
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(UserNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    //handleMethdexcpn as argument with @Valid has gone wrong in the method
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
    HttpHeaders headers, HttpStatusCode status, WebRequest request){

        //copying same defination in above exceptions and only changing the message
        //by giving the details of what has went wrong(stored in binding result)
        //If we don't want to send much info in the message, instead of ex.getMessage(), give the message:"Validation Failed"
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
        ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    
}
