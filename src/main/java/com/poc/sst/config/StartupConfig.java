package com.poc.sst.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoDatabase;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/*
@Configuration
public class StartupConfig {

    @Value("${account-db.database}")
    String dbName;

    @Autowired
    MongoClient mongoClient;

    @PostConstruct
    public void initDatabase() {
        final String collectionName = "accounts";
        MongoDatabase db = mongoClient.getDatabase(dbName);

        Mono.from(db.listCollectionNames())
                .filter(name -> name.equals(collectionName))
                .hasElement()
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.from(db.createCollection(collectionName)).then();
                    }
                    return Mono.empty();
                })
                .subscribe(); // без этого ничего не произойдёт
    }

}
*/