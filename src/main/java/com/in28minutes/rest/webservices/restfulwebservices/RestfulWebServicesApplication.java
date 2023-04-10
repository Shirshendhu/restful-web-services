package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	//For internationalization(Showing different lang for different countries)
	@Bean
	public LocaleResolver localeResolver(){
		//SessionLocaleResolver when locale is passed as argument in helloWorldInternationalized
		//Else, use, AcceptHeaderLocaleResolver
		//SessionLocaleResolver localeResolver=new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
		//Default local lang to be used
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	
	//This config can be ommitted and can be used inside applications.properties itself.
	//To configure messages.properties files based on Input accepted header
	/*
	@Bean//Alert: name of the method -> should be messageSource
	public ResourceBundleMessageSource MessageSource(){
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
		//To tell what is the name of the message, after this keyword, rest will be used to identify which lang/properties file
		//is reqd. eg: nothing/random value in header shows default file of messages.properties, fr in Accept-Language header value
		//will route to message_fr.properties as by default, springboot checks resources directory and format  of files should be of *_locale.properties
		
		messageSource.setBasename("messages");
		return messageSource;
	}*/

}
