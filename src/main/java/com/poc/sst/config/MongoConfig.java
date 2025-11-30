package com.poc.sst.config;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/*
@Configuration
@EnableReactiveMongoRepositories(
        basePackages = "com.poc.sst.person",
        reactiveMongoTemplateRef = "personMongoTemplate"
)
public class MongoConfig {

    @Bean
    public MongoClient personMongoClient(@Value("${person-db.uri}") String uri) {
        return MongoClients.create(uri);
    }

    @Bean(name = "personMongoTemplate")
    public ReactiveMongoTemplate personMongoTemplate(
            MongoClient personMongoClient,
            @Value("${person-db.database}") String dbName) {
        return new ReactiveMongoTemplate(personMongoClient, dbName);
    }
}
*/