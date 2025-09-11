package com.mrnm.sys.blog.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.mrnm.sys.blog.repository")
public class MongoInstanceConfig {

    @Value("${mongo.property.user}")
    private String user;

    @Value("${mongo.property.secret}")
    private String secret;

    @Qualifier("MongoClient")
    public @Bean MongoClient mongoClient() {
        String connectionString = String.format("mongodb+srv://%s:%s@cluster0.gtysmx4.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0", user, secret);
        return MongoClients.create(connectionString);
    }

}

