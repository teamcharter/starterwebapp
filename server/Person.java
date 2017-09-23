package server;

import org.json.simple.JSONObject;

public class Person {
    
    private String name;

    Person(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
}