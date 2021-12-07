package com.payment.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
public class DataSourceConfig {

	@Autowired
	private AppProperties appProperties;

	@Bean
	public MongoClient mongo() {
		ConnectionString connectionString = new ConnectionString(this.connectionURL());
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();

		return MongoClients.create(mongoClientSettings);
	}

	@Bean("reactiveMongoTemplate")
	public ReactiveMongoTemplate mongoTemplate() throws Exception {
		return new ReactiveMongoTemplate(this.mongo(), appProperties.getDatabase());
	}

	private String connectionURL() {
		return "mongodb://" + appProperties.getDbHost() + ":" + appProperties.getDbPort() + "/"
				+ appProperties.getDatabase();
	}
}
