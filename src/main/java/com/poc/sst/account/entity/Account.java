package com.poc.sst.account.entity;

import com.poc.sst.primitive.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document(collection="accounts")
@Getter
@Setter
public class Account {

    @Id
    private String id;

    private String name;

    private String personId;

    private BigDecimal saldo;

    private PersonInfo personInfo;

    private Long timeStamp;

    public Account(String id, String personId, String name) {
        this.setId(id);
        this.setPersonId(personId);
        this.setName(name);
    }

    public static Account create(String personId, String name){
        var id = "Account-"+ UUID.randomUUID();
        return new Account(id, personId, name);
    }
}
