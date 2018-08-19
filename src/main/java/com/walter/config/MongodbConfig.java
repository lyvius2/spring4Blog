package com.walter.config;

import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

/**
 * MongoDB Setting JAVA Config
 * Created by Walter on 2018-08-20.
 */
@Configuration
@PropertySource("classpath:/application.properties")
@EnableMongoRepositories(value = {"com.walter.repository"}, mongoTemplateRef = "mongoTemplate")
public class MongodbConfig extends AbstractMongoConfiguration {

	@Value("${mongodb.uri}")
	private String mongodb_uri;

	@Override
	protected String getDatabaseName() {
		return "travels_log";
	}

	@Bean
	public MongoURI mongoURI() {
		return new MongoURI(mongodb_uri);
	}

	@Bean
	public Mongo mongo() throws UnknownHostException {
		return new Mongo(mongoURI());
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		return new MongoTemplate(mongo(), getDatabaseName());
	}
}
