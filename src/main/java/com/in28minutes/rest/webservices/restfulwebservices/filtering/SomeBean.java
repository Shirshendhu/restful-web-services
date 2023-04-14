package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//The ignoring can also be defined at the class level instead of fields
//If 2 values are needed then it becomes array so, need to make it array by adding {} braces
//Ranga prefers Field level instead of class level, as if field name changes later, no need to search anywhere else..
//@JsonIgnoreProperties({"field1","field2"})
//To filter dynamically using SimpleFIlterProvider:
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    //To do static filtering, do @JsonIgnore. Same thing happens when passed in a list
    //@JsonIgnore
    private String field2;
    private String field3;
    public String getField1() {
        return field1;
    }
    public String getField2() {
        return field2;
    }
    public String getField3() {
        return field3;
    }
    @Override
    public String toString() {
        return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
    }
    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }
}
