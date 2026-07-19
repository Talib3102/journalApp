package com.talib.journalApp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    CommandLineRunner checkMongoDatabase(MongoTemplate mongoTemplate) {
        return args -> {
            System.out.println("======================================");
            System.out.println("MongoDB Database: " + mongoTemplate.getDb().getName());
            System.out.println("======================================");
        };
    }
}