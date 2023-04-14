package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public MappingJacksonValue filtering(){
        //Instead of doing filtering in bean(static), but to do filtering differently in controller,
        //MappingJacksonValue is used to pass specific serialization instruction to the Jackson converter.

        SomeBean someBean = new SomeBean("value1","value2","value3");
        //Well return someBean by filtering something.
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(someBean);
        //Define filter accordingly
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        //FilterProvider allows to execute a number of filters as passed
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList(){
        List<SomeBean> asList = Arrays.asList(new SomeBean("value1", "value2", "value3"),new SomeBean("value4", "value5", "value6"));
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(asList);

        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        //FilterProvider allows to define a number of filters
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue ;
    }
}