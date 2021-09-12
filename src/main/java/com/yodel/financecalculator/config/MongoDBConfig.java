package com.yodel.financecalculator.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.yodel.financecalculator.repository.InterestCalculationResultRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.lang.String.format;

@EnableMongoRepositories(basePackageClasses = InterestCalculationResultRepository.class)
@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Value(value = "${spring.data.mongo.database}")
    private String databaseName;


    @Value(value = "${spring.data.mongo.host}")
    private String hostName;


    @Value(value = "${spring.data.mongo.port}")
    private String port;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public MongoClient mongoClient() {

        ConnectionString connectionString = new ConnectionString(format("mongodb://%s:%s/%s", hostName, port, databaseName));
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

}

