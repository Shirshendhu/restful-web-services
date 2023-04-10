package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller

//Need to tell Spring MVC that the controller will handle rest requests
@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;

    //GET "Hello World"

    //method to return HelloWorld
    //Mapping to tell that GET request will map to the given URI
    //@RequestMapping(method = RequestMethod.GET, path="/hello-world")
    @GetMapping(path="/hello-world")
    public String HelloWorld(){
        return "Hello World";
    }

    //Passing a bean instead of string
    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    //Passing a path parameter in resource mapping
    //hello-world/path-variable/in28minutes
    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }

    @GetMapping(path="/hello-world-internationalized")
    //what is the Request header that contains Locale?->Accept-Language
    //Here, locale is passed as a parameter, but it'll be difficult to create for all the methods so..
    /*public String helloWorldInternationalized(@RequestHeader(name="Accept-Language",required=false) Locale locale){
        return messageSource.getMessage("good.morning.message",null, locale);
    }*/
    //..Spring provides an alternative to not pick from requestheader, rather get it from LocaleContextHolder
    public String helloWorldInternationalized(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
}
