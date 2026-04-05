package com.siva.movies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * MongoDB Configuration
 * Configures MongoClient to connect to MongoDB Atlas using credentials from .env file
 */
@Configuration
public class MongodbConfig {
    
    // MongoDB Atlas credentials from .env file
    @Value("${MONGO_USER:}")
    private String mongoUser;
    
    @Value("${MONGO_PASSWORD:}")
    private String mongoPassword;
    
    @Value("${MONGO_CLUSTER:}")
    private String mongoCluster;
    
    // MongoDB database name
    @Value("${MONGO_DATABASE:test}")
    private String database;
    
    /**
     * Create MongoDB Client connection
     * If credentials are provided, connects to MongoDB Atlas
     * Otherwise connects to local MongoDB
     */
    @Bean
    public MongoClient mongoClient() {
        String connectionString;
        
        if (mongoUser != null && !mongoUser.isEmpty() && mongoCluster != null && !mongoCluster.isEmpty()) {
            // MongoDB Atlas connection
            connectionString = String.format("mongodb+srv://%s:%s@%s/?retryWrites=true&w=majority", 
                mongoUser, mongoPassword, mongoCluster);
        } else {
            // Local MongoDB fallback
            connectionString = "mongodb://localhost:27017";
        }
        
        return MongoClients.create(connectionString);
    }
    
    /**
     * Configure the MongoDB database factory
     * Specifies which database to use
     */
    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, database);
    }
}
