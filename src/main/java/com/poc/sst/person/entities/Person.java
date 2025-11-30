package com.poc.sst.person.entities;

import com.poc.sst.primitive.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection="persons")
@Getter
@Setter(AccessLevel.PROTECTED)
public class Person {
    @Id
    private String id;
    private String name;
    private String inn;
    private Long timeStamp;

    private Person(String id, String name, String inn){
        setId(id);
        setName(name);
        setInn(inn);
    }

    public static Person create(String name, String inn){
        var id = "Person-"+UUID.randomUUID();
        return new Person(id, name, inn);
    }

    public void setName(String newName){
        if (newName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Name can't contain digits");
        }
        this.name = newName;
    }

    public void setInn(String newInn) {
        inn = newInn.replaceAll("[^0-9]", "");
    }
}
